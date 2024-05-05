package com.flashwizserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "folder")
public class Folder implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(length = 128, nullable = false)
	private String name;
	
	@Column(length = 128, nullable = false)
	private String descriptions;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	

	@OneToMany(mappedBy = "folder",cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Flashcard> flashcards = new ArrayList<>(); 


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public List<Flashcard> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(List<Flashcard> flashcards) {
		this.flashcards = flashcards;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}