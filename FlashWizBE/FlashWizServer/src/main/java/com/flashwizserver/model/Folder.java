package com.flashwizserver.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.*;

@Entity
@Table(name = "folder")
public class Folder {
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
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "folder_flashcard",
	           joinColumns = @JoinColumn(name = "folder_id"),
	           inverseJoinColumns = @JoinColumn(name = "flashcard_id"))
	private Set<Flashcard> flashcards = new HashSet<>();
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Flashcard> getFlashcard() {
		return flashcards;
	}

	public void setFlashcard(Set<Flashcard> flashcards) {
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
