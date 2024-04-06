package com.flashwizserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashwizserver.model.Flashcard;
import com.flashwizserver.repository.FlashcardRepository;

@Service
public class FlashcardDAO {
	 	@Autowired
	    private FlashcardRepository flashcardRepository;

	    public List<Flashcard> getAllFlashcards() {
	        return (List<Flashcard>) flashcardRepository.findAll();
	    }

	    public Flashcard getFlashcardById(Integer id) {
	        return flashcardRepository.findById(id).orElse(null);
	    }

	    public Flashcard createFlashcard(Flashcard flashcard) {
	        return flashcardRepository.save(flashcard);
	    }

	    public Flashcard updateFlashcard(Integer id, Flashcard flashcard) {
	        if (flashcardRepository.existsById(id)) {
	            flashcard.setId(id);
	            return flashcardRepository.save(flashcard);
	        }
	        return null;
	    }

	    public void deleteFlashcard(Integer id) {
	        flashcardRepository.deleteById(id);
	    }
}
