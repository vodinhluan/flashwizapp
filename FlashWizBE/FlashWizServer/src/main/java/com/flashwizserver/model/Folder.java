package com.flashwizserver.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
<<<<<<< HEAD
	@OneToMany(mappedBy = "folder") // Một folder có nhiều flashcard
    private List<Flashcard> flashcards = new ArrayList<>(); // Thay vì Set<Flashcard>
   
=======
	@OneToMany(mappedBy = "folder") 
    private List<Flashcard> flashcards = new ArrayList<>(); 


>>>>>>> b96a23905502acf46685e6f51ff9c5a0f1ee9888
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
<<<<<<< HEAD
=======

>>>>>>> b96a23905502acf46685e6f51ff9c5a0f1ee9888
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
