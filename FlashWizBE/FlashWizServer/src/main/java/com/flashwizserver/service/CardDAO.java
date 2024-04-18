package com.flashwizserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashwizserver.model.Card;
import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Folder;
import com.flashwizserver.repository.CardRepository;

@Service
public class CardDAO {
	@Autowired
	private CardRepository cardRepo;
	
	public List<Card> getAllCards() {
		return (List<Card>) cardRepo.findAll();
	}
	
	public Card getCardByUd(Integer id) {
		return cardRepo.findById(id).orElse(null);
	}
	
	public Card createCard (Card card) {
		card.setRating("new");
		return cardRepo.save(card);
	}
	
	public Card updateCard(Integer id, Card card) {
		if (cardRepo.existsById(id)) {
			card.setId(id);
			return cardRepo.save(card);
		}
		return null;
	}
	
	public void deleteCard(Integer id) {
		cardRepo.deleteById(id);
	}
	
}
