package com.flashwizserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Folder;
import com.flashwizserver.model.User;
import com.flashwizserver.service.FlashcardDAO;
import com.flashwizserver.service.FolderDAO;
import com.flashwizserver.service.UserDAO;
@RestController
public class FlashcardController {
    @Autowired
    private FlashcardDAO flashcardService;
    @Autowired
    private FolderDAO folderService;
    @GetMapping("/flashcard/get-all")
    public ResponseEntity<List<Flashcard>> getAllFlashcards() {
        List<Flashcard> flashcards = flashcardService.getAllFlashcards();
        return new ResponseEntity<>(flashcards, HttpStatus.OK);
    }

  
    @PostMapping("/flashcard/save")
    public ResponseEntity<Flashcard> createFlashcard(@RequestBody Flashcard flashcard, @RequestParam("userId") Integer userId, @RequestParam("folderIds") List<Integer> folderIds) {
        // Tạo đối tượng User từ userId
        User user = new User();
        user.setId(userId);
        flashcard.setUser(user);

        // Tạo danh sách các folder từ danh sách folderIds và lưu chúng vào cơ sở dữ liệu
        List<Folder> folders = new ArrayList<>();
        for (Integer folderId : folderIds) {
          Folder folder = folderService.findById(folderId);
          if (folder != null) {
            folders.add(folder);
          }
        }
      
        for (Folder folder : folders) {
        	  folder.getFlashcard().add(flashcard);
        	}
        // Gán danh sách folder cho flashcard
        flashcard.setFolders(folders);

        // Lưu flashcard
        Flashcard createdFlashcard = flashcardService.createFlashcard(flashcard);
        return new ResponseEntity<>(createdFlashcard, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Integer id) {
        flashcardService.deleteFlashcard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }}
