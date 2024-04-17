package com.flashwizserver.model;

public class CardRequest {
	private Integer id;
	private String front;
	private String back;
	private Integer flashcardId;
	public CardRequest(Integer id2, String front2, String back2, Integer id3) {
		// TODO Auto-generated constructor stub
	}
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
	public Integer getFlashcardId() {
		return flashcardId;
	}
	public void setFlashcardId(Integer flashcardId) {
		this.flashcardId = flashcardId;
	}

	
}
