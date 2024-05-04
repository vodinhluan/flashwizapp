package com.flashwizserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Folder;
import com.flashwizserver.model.User;
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

	public Flashcard findById(Integer flashcardId) {
		return flashcardRepository.findById(flashcardId).orElse(null);
	}

	public Flashcard saveFlashcard(Flashcard flashcard) {
		if (flashcard.getDescriptions() == null) {
			flashcard.setDescriptions("");
		}
		return flashcardRepository.save(flashcard);
	}
	
}
