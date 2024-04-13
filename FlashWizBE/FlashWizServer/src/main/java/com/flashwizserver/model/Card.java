package com.flashwizserver.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column
	private String front;
	
	@Column
	private String back;
	
//	@JsonIgnore
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "flashcard_id") 
//	private List<Flashcard> flashcards;
	@JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "flashcard_id") 
    private Flashcard flashcard;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

//	public List<Flashcard> getFlashcards() {
//		return flashcards;
//	}
//
//	public void setFlashcards(List<Flashcard> flashcards) {
//		this.flashcards = flashcards;
//	}
	public Flashcard getFlashcard() {
		return flashcard;
	}

	public void setFlashcard(Flashcard flashcard) {
		this.flashcard = flashcard;
	}

	
	
	
}
