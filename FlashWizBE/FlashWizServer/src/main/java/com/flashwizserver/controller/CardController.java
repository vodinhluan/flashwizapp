package com.flashwizserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flashwizserver.model.Card;
import com.flashwizserver.repository.CardRepository;
import com.flashwizserver.service.CardDAO;

@RestController
public class CardController {
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private CardDAO cardService;
    
    @GetMapping("/card/get-all")
    public ResponseEntity<List<Card>> getAllCards() {
    	List<Card> cards = cardService.getAllCards();
    	return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping("/card/save")
    public Card createCard(@RequestBody Card card) {
        return cardRepository.save(card);
    }
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCard(@PathVariable Integer id) {
//        cardService.deleteCard(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
