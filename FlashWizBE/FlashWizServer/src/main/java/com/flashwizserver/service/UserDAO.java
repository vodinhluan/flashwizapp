package com.flashwizserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashwizserver.model.User;
import com.flashwizserver.repository.UserRepository;

@Service
public class UserDAO {
	@Autowired
	private UserRepository userRepo;

	public List<User> listUser() {
		return (List<User>) userRepo.findAll();
	}

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public void deleteUser(User user) {
		userRepo.delete(user);
	}

}



