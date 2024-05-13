package com.flashwizserver.model;

public class AuthResponse {
	private Integer id;
	private String name;
	private String email;
	private String accessToken;


	
	
	public AuthResponse() {}
	
	public AuthResponse(Integer id,String name, String email, String accessToken) {
		super();
		this.id=id;
		this.name = name;
		this.email = email;
		this.accessToken = accessToken;
	}
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}