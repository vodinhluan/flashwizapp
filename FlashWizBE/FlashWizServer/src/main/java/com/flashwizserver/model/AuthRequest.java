package com.flashwizserver.model;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRequest {
	private String email;
	private String password;
=======
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;

public class AuthRequest {
	@Email @Length(min = 5, max = 50)
	private String email;
	
	@Length(min = 5, max = 10)
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
>>>>>>> origin/dev
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
<<<<<<< HEAD
=======
	
>>>>>>> origin/dev
}
