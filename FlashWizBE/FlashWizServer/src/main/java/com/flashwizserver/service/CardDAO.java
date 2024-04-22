package com.flashwizserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashwizserver.model.Card;
import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Folder;
import com.flashwizserver.repository.CardRepository;
import com.flashwizserver.security.ResourceNotFoundException;

@Service
public class CardDAO {
	@Autowired
	private CardRepository cardRepo;

	public List<Card> getAllCards() {
		return (List<Card>) cardRepo.findAll();
	}

	public Optional<Card> getCardById(Integer id) {
		return cardRepo.findById(id);
	}

	public Card createCard(Card card) {
		card.setRating("new");
		return cardRepo.save(card);
	}

	public Card updateCard(Integer id, Card card) {
		if (cardRepo.existsById(id)) {
			Card existingCard = cardRepo.findById(id).get();
			existingCard.setRating(card.getRating());
			return cardRepo.save(existingCard);
		}
		return null;
	}

	public void deleteCard(Integer id) {
		cardRepo.deleteById(id);
	}

	// Update Rating
	public Card updateRating(Integer id, Card card) {
		Optional<Card> optionalExistingCard = cardRepo.findById(id);
		if (optionalExistingCard.isPresent()) {
			Card existingCard = optionalExistingCard.get();
			existingCard.setRating(card.getRating());
			return cardRepo.save(existingCard);
		} else {
			return null;
		}
	}

}
