package com.flashwizserver.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.flashwizserver.model.Card;
import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Folder;
import com.flashwizserver.repository.CardRepository;
import com.flashwizserver.service.CardDAO;
import com.flashwizserver.service.FlashcardDAO;

@RestController
public class CardController {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CardDAO cardService;

	@Autowired
	private FlashcardDAO flashcardService;

	@GetMapping("/card/get-all")
	public ResponseEntity<List<Card>> getAllCards() {
		List<Card> cards = cardService.getAllCards();
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}

	@PostMapping("/card/save")
	public ResponseEntity<Card> createCard(@RequestBody Card card, @RequestParam("flashcardId") Integer flashcardId) {
		Flashcard flashcard = flashcardService.findById(flashcardId);
		if (flashcard == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		card.setFlashcard(flashcard);
		Card createdCard = cardService.createCard(card);
		flashcard.getCard().add(createdCard);
		flashcardService.saveFlashcard(flashcard);

		return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
	}

	@GetMapping("/card/get-by-flashcard/{flashcardId}")
	public ResponseEntity<List<Card>> getCardsByFlashcardId(@PathVariable("flashcardId") Integer flashcardId) {
		Flashcard flashcard = flashcardService.findById(flashcardId);
		if (flashcard == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<Card> cards = flashcard.getCard();

		return new ResponseEntity<>(cards, HttpStatus.OK);
	}

	@DeleteMapping("/card/delete/{id}")
	public ResponseEntity<List<Card>> deleteCard(@PathVariable(name = "id") Integer id) {
		cardService.deleteCard(id);
		List<Card> cards = cardService.getAllCards();
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}

	@GetMapping("/card/{id}")
	public ResponseEntity<Card> getCardById(@PathVariable(name = "id") Integer id) {
		Optional<Card> cardOptional = cardService.getCardById(id);
		if (!cardOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cardOptional.get(), HttpStatus.OK);
	}

	// Update Rating
	@PostMapping("/card/{id}/rating")
	public ResponseEntity<Card> updateRating(@PathVariable("id") Integer id, @RequestParam("rating") String rating) {
		Optional<Card> cardOptional = cardService.getCardById(id);
		if (!cardOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Card card = cardOptional.get();
		card.setRating(rating);
		card = cardService.updateCard(id, card);
		return ResponseEntity.ok().body(card);
	}
	
	@GetMapping("/card/{flashcardId}/statistic")
	public ResponseEntity<Map<String, Integer>> getCardStatistics(@PathVariable("flashcardId") Integer flashcardId) {
	    Flashcard flashcard = flashcardService.findById(flashcardId);
	    if (flashcard == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    List<Card> cards = flashcard.getCard();

	    Map<String, Integer> statistics = new LinkedHashMap<>();
	    statistics.put("totalCard", (int) cards.size());
	    statistics.put("newCard",   (int) cards.stream().filter(card -> "new".equals(card.getRating())).count());
	    statistics.put("failCard",  (int) cards.stream().filter(card -> "fail".equals(card.getRating())).count());
	    statistics.put("hardCard",  (int) cards.stream().filter(card -> "hard".equals(card.getRating())).count());
	    statistics.put("goodCard",  (int) cards.stream().filter(card -> "good".equals(card.getRating())).count());
	    statistics.put("easyCard",  (int) cards.stream().filter(card -> "easy".equals(card.getRating())).count());

	    return new ResponseEntity<>(statistics, HttpStatus.OK);
	}
}
