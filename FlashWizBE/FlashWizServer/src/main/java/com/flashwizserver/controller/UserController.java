package com.flashwizserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.flashwizserver.model.User;
import com.flashwizserver.service.UserDAO;

@RestController
public class UserController {
	@Autowired
	private UserDAO userDAO;

	@GetMapping("/user/get-all")
	public List<User> getAllUsers() {
		return userDAO.listUser();
	}

	@PostMapping("/user/save") 
	public void save(@RequestBody User user) {
		userDAO.saveUser(user);
	}
}