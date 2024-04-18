package com.flashwizserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Folder;
import com.flashwizserver.model.User;
import com.flashwizserver.service.FolderDAO;
import com.flashwizserver.service.UserDAO;
@RestController
public class FolderController {
    @Autowired
    private FolderDAO folderDAO;
    @Autowired
    private UserDAO userDAO;
    @GetMapping("/folder/get-all")
    public ResponseEntity<List<Folder>> getAllFolder() {
        List<Folder> folder = folderDAO.getAllFolder();
        return new ResponseEntity<>(folder, HttpStatus.OK);
    }
    @GetMapping("/folder/get/{userId}")
    public ResponseEntity<List<Folder>> getFolderByUserId(@PathVariable("userId") Integer userId) {
        List<Folder> folders = folderDAO.getFolderByUserId(userId);
        if (folders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }

//	@GetMapping("/folder/get-all")
//	public ResponseEntity<?> getAllFoldersForCurrentUser() {
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    Object principal = authentication.getPrincipal();
//	    Integer userId = null;
//
//	    if (principal instanceof UserDetails) {
//	        String userEmail = ((UserDetails) principal).getUsername();
//	        User user = userDAO.getUserByEmail(userEmail);
//	        userId = user.getId();
//	    }
//
//	    if (userId != null) {
//	        List<Folder> folders = folderDAO.getAllFoldersForUser(userId);
//	        return new ResponseEntity<>(folders, HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//	    }
//	}


    @PostMapping("/folder/save")
    public ResponseEntity<Folder> saveFolder(@RequestBody Folder folder, @RequestParam("userId") Integer userId) {
        User user = new User();
        user.setId(userId);
        folder.setUser(user);
        
        Folder savedFolder = folderDAO.saveFolder(folder);
        return ResponseEntity.ok(savedFolder);
    }
    @DeleteMapping("/folder/delete/{id}")
    public ResponseEntity<List<Folder>> deleteFolder(@PathVariable(name= "id") Integer id) {
        folderDAO.delete(id);
        List<Folder> folders = folderDAO.getAllFolder();
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }


   
}




