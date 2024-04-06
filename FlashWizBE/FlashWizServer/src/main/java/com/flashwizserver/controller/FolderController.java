package com.flashwizserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashwizserver.model.Folder;
import com.flashwizserver.model.User;
import com.flashwizserver.service.FolderDAO;
import com.flashwizserver.service.UserDAO;
@RestController
public class FolderController {
    @Autowired
    private FolderDAO folderDAO;
   


    @GetMapping("/folder/get-all")
    public List<Folder> getAllFolders() {
        return folderDAO.listFolder();
    }

    @PostMapping("/folder/save")
    public ResponseEntity<Folder> saveFolder(@RequestBody Folder folder, @RequestParam("userId") Integer userId) {
        User user = new User();
        user.setId(userId);
        folder.setUser(user);
        
        Folder savedFolder = folderDAO.saveFolder(folder);
        return ResponseEntity.ok(savedFolder);
    }}

