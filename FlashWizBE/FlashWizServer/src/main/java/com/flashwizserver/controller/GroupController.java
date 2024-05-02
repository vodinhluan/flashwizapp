package com.flashwizserver.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Group;
import com.flashwizserver.model.GroupDTO;
import com.flashwizserver.model.GroupFlashcard;
import com.flashwizserver.model.GroupUser;
import com.flashwizserver.model.User;
import com.flashwizserver.repository.FlashcardRepository;
import com.flashwizserver.repository.GroupFlashcardRepository;
import com.flashwizserver.repository.GroupRepository;
import com.flashwizserver.repository.GroupUserRepository;
import com.flashwizserver.repository.UserRepository;
import com.flashwizserver.service.GroupDAO;

@RestController
public class GroupController {
	@Autowired
    private GroupRepository groupRepository;
	
	@Autowired
    private GroupFlashcardRepository groupFlashcardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FlashcardRepository flashcardRepository;
	
	
	@Autowired
	private GroupUserRepository groupUserRepository;
	
	@Autowired
    private GroupDAO groupDAO;

	@PostMapping("/{userId}/group/create")
    public ResponseEntity<?> createGroup(@PathVariable("userId") Integer userId, @RequestBody Group group) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        
        group.setGroupName(group.getGroupName());
        group.setGroupCode(groupDAO.generateRandomGroupCode());
        
        group = groupRepository.save(group);

        GroupUser groupUser = new GroupUser();
        groupUser.setUser(user);
        groupUser.setGroup(group);

        groupUser = groupUserRepository.save(groupUser);
      
        GroupDTO groupDto = GroupDTO.fromGroup(group);
        return new ResponseEntity<>(groupDto, HttpStatus.CREATED);
    }

	@GetMapping("/group/list")
	public List<GroupDTO> getAllGroups() {
	    return groupRepository.findAll().stream()
	            .map(GroupDTO::fromGroup)
	            .collect(Collectors.toList());
	}
	
	@GetMapping("/group/user/{user_id}")
	public ResponseEntity<List<GroupDTO>> getUserGroups(@PathVariable("user_id") Integer userId) {
	    User user = userRepository.findById(userId).orElse(null);
	    
	    if (user != null) {
	        List<GroupUser> groupUsers = groupUserRepository.findByUser(user);
	    
	        // Convert to list of GroupDto
	        List<GroupDTO> userGroups = groupUsers.stream()
	            .map(groupUser -> new GroupDTO(groupUser.getGroup().getId(), groupUser.getGroup().getGroupName(), groupUser.getGroup().getGroupCode()))
	            .collect(Collectors.toList());

	        return ResponseEntity.ok(userGroups);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
    
    @PostMapping("/{userId}/group/join/{groupCode}")
    public ResponseEntity<?> joinGroup(
        @PathVariable("userId") Integer userId, 
        @PathVariable("groupCode") String groupCode
    ) {
        // Tìm user từ userId
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        // Tìm group từ groupCode
        Group group = groupRepository.findByGroupCode(groupCode)
            .orElseThrow(() -> new IllegalArgumentException("Invalid group code:" + groupCode));
        
        // Kiểm tra xem user đã trong group chưa
        List<GroupUser> userGroups = groupUserRepository.findByUserAndGroup(user, group);
        if (!userGroups.isEmpty()) {
            return new ResponseEntity<>("User đã là thành viên của nhóm", HttpStatus.BAD_REQUEST);
        }
        // Thêm user vào group vào GroupUser groupUser = new GroupUser();
        GroupUser groupUser = new GroupUser();
        groupUser.setUser(user);
        groupUser.setGroup(group);
        groupUserRepository.save(groupUser);

        // Sử dụng DTO để trả về group
        GroupDTO groupDto = GroupDTO.fromGroup(group);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);    }
    
    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> getGroup(@PathVariable("groupId") Integer groupId) {
        // Tìm nhóm bằng cách sử dụng id
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (!optionalGroup.isPresent()) {
            return new ResponseEntity<>("Nhóm không tồn tại", HttpStatus.NOT_FOUND);
        }
        Group group = optionalGroup.get();

        // Lấy danh sách người dùng trong nhóm
        Set<GroupUser> groupUsers = group.getUserGroups();

        // Duyệt qua danh sách người dùng và thu thập id của họ
        List<Integer> userIds = groupUsers.stream()
                .map(userGroup -> userGroup.getUser().getId())
                .collect(Collectors.toList());

        // Tạo một đối tượng Map để lưu trữ thông tin nhóm và thành viên
        Map<String, Object> groupInfo = new LinkedHashMap<>();
        groupInfo.put("groupId", group.getId());
        groupInfo.put("groupName", group.getGroupName());
        groupInfo.put("userIds", userIds);

        return new ResponseEntity<>(groupInfo, HttpStatus.OK);
    }
    
    @PostMapping("/groups/{group_id}/flashcards/{flashcard_id}/share")
    public ResponseEntity shareFlashcard(@PathVariable("group_id") Integer groupId, @PathVariable("flashcard_id") Integer flashcardId) {
       GroupFlashcard groupFlashcard = new GroupFlashcard();

       Group group = groupRepository.findById(groupId).orElse(null);
       Flashcard flashcard = flashcardRepository.findById(flashcardId).orElse(null);

       if (group != null && flashcard != null) {
           groupFlashcard.setGroup(group);
           groupFlashcard.setFlashcard(flashcard);
           groupFlashcardRepository.save(groupFlashcard);
           return ResponseEntity.status(HttpStatus.CREATED).build();
       } else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
