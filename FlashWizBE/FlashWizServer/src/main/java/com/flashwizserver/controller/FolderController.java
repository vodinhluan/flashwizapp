package com.flashwizserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/folder/get-all")
    public ResponseEntity<List<Folder>> getAllFolder() {
        List<Folder> folder = folderDAO.getAllFolder();
        return new ResponseEntity<>(folder, HttpStatus.OK);
    }


    @PostMapping("/folder/save")
    public ResponseEntity<Folder> saveFolder(@RequestBody Folder folder, @RequestParam("userId") Integer userId) {
        User user = new User();
        user.setId(userId);
        folder.setUser(user);
        
        Folder savedFolder = folderDAO.saveFolder(folder);
        return ResponseEntity.ok(savedFolder);
<<<<<<< HEAD
=======
    }
    @GetMapping("/folder/get-by-id/{folderId}")
    public ResponseEntity<Folder> getFolderById(@PathVariable Integer folderId) {
        Folder folder = folderDAO.getFolderById(folderId);
        if (folder != null) {
            return ResponseEntity.ok(folder);
        } else {
            return ResponseEntity.notFound().build();
        }
>>>>>>> b96a23905502acf46685e6f51ff9c5a0f1ee9888
    }
    @GetMapping("/folder/get-by-id/{folderId}")
    public ResponseEntity<Folder> getFolderById(@PathVariable Integer folderId) {
        Folder folder = folderDAO.getFolderById(folderId);
        if (folder != null) {
            return ResponseEntity.ok(folder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
<<<<<<< HEAD
}
=======
}


<<<<<<< HEAD
>>>>>>> b96a23905502acf46685e6f51ff9c5a0f1ee9888
=======
>>>>>>> b96a23905502acf46685e6f51ff9c5a0f1ee9888
