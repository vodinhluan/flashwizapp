package com.flashwizserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flashwizserver.model.Group;
import com.flashwizserver.repository.GroupRepository;
import com.flashwizserver.service.GroupDAO;

@RestController
public class GroupController {
	@Autowired
    private GroupRepository groupRepository;
	@Autowired
    private GroupDAO groupDAO;

    @PostMapping("/group/create")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        String groupCode = groupDAO.generateRandomGroupCode();
        group.setGroupCode(groupCode);

        Group createdGroup = groupRepository.save(group);
        return ResponseEntity.ok(createdGroup);
    }

    @GetMapping("/group/list")
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
