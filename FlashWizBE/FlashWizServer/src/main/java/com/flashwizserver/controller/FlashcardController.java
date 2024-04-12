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
    public ResponseEntity<Flashcard> createFlashcard(@RequestBody Flashcard flashcard, @RequestParam("userId") Integer userId, @RequestParam("folderId") Integer folderId) {
        User user = new User();
        user.setId(userId);
        flashcard.setUser(user);

        // Lấy thông tin về folder từ folderId
        Folder folder = folderService.findById(folderId);
        if (folder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        flashcard.setFolder(folder);

        Flashcard createdFlashcard = flashcardService.createFlashcard(flashcard);

        folder.getFlashcards().add(createdFlashcard);
        folderService.saveFolder(folder);

        return new ResponseEntity<>(createdFlashcard, HttpStatus.CREATED);
    }


 
    @GetMapping("/flashcard/get-by-folder/{folderId}")
    public ResponseEntity<List<Flashcard>> getFlashcardsByFolderId(@PathVariable("folderId") Integer folderId) {
        // Lấy thông tin về folder từ folderId
        Folder folder = folderService.findById(folderId);
        if (folder == null) {
            // Nếu không tìm thấy folder, trả về lỗi
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Lấy danh sách flashcards của folder
        List<Flashcard> flashcards = folder.getFlashcards();
        
        // Trả về danh sách flashcards
        return new ResponseEntity<>(flashcards, HttpStatus.OK);
    }

    @DeleteMapping("/flashcard/delete/{id}")
    public ResponseEntity<List<Flashcard>> deleteFlashcard(@PathVariable(name= "id") Integer id) {
    	flashcardService.deleteFlashcard(id);
        List<Flashcard> flashcards = flashcardService.getAllFlashcards();
        return new ResponseEntity<>(flashcards, HttpStatus.OK);
    }
   }
