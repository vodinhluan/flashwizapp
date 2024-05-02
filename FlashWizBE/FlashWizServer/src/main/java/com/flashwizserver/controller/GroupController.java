package com.flashwizserver.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
import com.flashwizserver.model.Folder;
import com.flashwizserver.model.Group;
import com.flashwizserver.model.GroupDTO;
import com.flashwizserver.model.GroupFolder;
import com.flashwizserver.model.GroupUser;
import com.flashwizserver.model.User;
import com.flashwizserver.repository.FlashcardRepository;
import com.flashwizserver.repository.FolderRepository;
import com.flashwizserver.repository.GroupFolderRepository;
import com.flashwizserver.repository.GroupRepository;
import com.flashwizserver.repository.GroupUserRepository;
import com.flashwizserver.repository.UserRepository;
import com.flashwizserver.service.GroupDAO;

@RestController
public class GroupController {
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupFolderRepository groupFolderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private GroupUserRepository groupUserRepository;

	@Autowired
	private GroupDAO groupDAO;

	@PostMapping("/{userId}/group/create")
	public ResponseEntity<?> createGroup(@PathVariable("userId") Integer userId, @RequestBody String groupName) {
		// Tìm user từ userId
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

		// Tạo group mới
		Group group = new Group();
		group.setGroupName(groupName);
		group.setGroupCode(groupDAO.generateRandomGroupCode());

		// Lưu group vào database
		group = groupRepository.save(group);

		// Tạo GroupUser
		GroupUser groupUser = new GroupUser();
		groupUser.setUser(user);
		groupUser.setGroup(group);

		// Lưu GroupUser vào database
		groupUser = groupUserRepository.save(groupUser);

		GroupDTO groupDto = GroupDTO.fromGroup(group);
		return new ResponseEntity<>(groupDto, HttpStatus.CREATED);
	}

	@GetMapping("/group/list")
	public List<GroupDTO> getAllGroups() {
		return groupRepository.findAll().stream().map(GroupDTO::fromGroup).collect(Collectors.toList());
	}

	@GetMapping("/group/user/{userId}")
	public ResponseEntity<List<GroupDTO>> getUserGroups(@PathVariable("userId") Integer userId) {
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
	public ResponseEntity<?> joinGroup(@PathVariable("userId") Integer userId,
			@PathVariable("groupCode") String groupCode) {
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
		return new ResponseEntity<>(groupDto, HttpStatus.OK);
	}

	@GetMapping("/group/{groupId}")
	public ResponseEntity<?> getGroup(@PathVariable("groupId") Integer groupId) {
	    Optional<Group> optionalGroup = groupRepository.findById(groupId);
	    if (!optionalGroup.isPresent()) {
	        return new ResponseEntity<>("Nhóm không tồn tại", HttpStatus.NOT_FOUND);
	    }
	    Group group = optionalGroup.get();

	    Set<GroupUser> groupUsers = group.getUserGroups();

	    List<Integer> userIds = groupUsers.stream().map(userGroup -> userGroup.getUser().getId())
	            .collect(Collectors.toList());

	    // Get the group folders
	    List<GroupFolder> groupFolders = groupFolderRepository.findByGroup(group);
	    // Use Set to store unique folder ids
	    Set<Integer> folderIds = groupFolders.stream().map(groupFolder -> groupFolder.getFolder().getId())
	            .collect(Collectors.toSet());

	    Map<String, Object> groupInfo = new LinkedHashMap<>();
	    groupInfo.put("groupId", group.getId());
	    groupInfo.put("groupName", group.getGroupName());
	    groupInfo.put("userIds", userIds);
	    groupInfo.put("folderIds", folderIds);

	    return new ResponseEntity<>(groupInfo, HttpStatus.OK);
	}
	
	
	@PostMapping("/{userId}/groups/{groupId}/folders/{folderId}/share")
	public ResponseEntity<?> shareFolder(@PathVariable("groupId") Integer groupId,
	                                     @PathVariable("folderId") Integer folderId,
	                                     @PathVariable("userId") Integer userId) {

	    User user = userRepository.findById(userId)
	                               .orElseThrow(() -> new IllegalArgumentException("Invalid userId: " + userId));

	    Folder folder = folderRepository.findById(folderId)
	                                    .orElseThrow(() -> new IllegalArgumentException("Invalid folderId: " + folderId));

	    Group group = groupRepository.findById(groupId)
	                                 .orElseThrow(() -> new IllegalArgumentException("Invalid groupId: " + groupId));
	                                     
	    List<GroupUser> groupUsers = groupUserRepository.findByGroup(group);
	    
	    // Only share folder if it hasn't been shared yet
	    if (!groupFolderRepository.existsByFolderAndGroup(folder, group)) {
	        for (GroupUser groupUser : groupUsers) {
	            if (!groupUser.getUser().equals(user)) {
	                GroupFolder groupFolder = new GroupFolder();
	                groupFolder.setGroup(group);
	                groupFolder.setUser(groupUser.getUser());
	                groupFolder.setFolder(folder);
	                groupFolderRepository.save(groupFolder);
	            }
	        }
	    }

	    return new ResponseEntity<>(folderId, HttpStatus.OK); // Return only the shared folder ID
	} 
	
	@GetMapping("/group/{groupId}/folders")
	public ResponseEntity<?> getListFolderByGroupId(@PathVariable("groupId") Integer groupId) {
	    // Tìm nhóm từ groupId
	    Group group = groupRepository.findById(groupId)
	                                 .orElseThrow(() -> new IllegalArgumentException("Invalid groupId: " + groupId));

	    // Lấy danh sách thư mục của nhóm
	    List<GroupFolder> groupFolders = groupFolderRepository.findByGroup(group);
	    Set<Folder> folderSet = groupFolders.stream().map(GroupFolder::getFolder).collect(Collectors.toCollection(LinkedHashSet::new));

	    // Trả về danh sách folder không trùng lặp
	    return new ResponseEntity<>(folderSet, HttpStatus.OK);
	}

}
