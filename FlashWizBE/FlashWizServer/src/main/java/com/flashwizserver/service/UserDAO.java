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

	public User findById(Integer userId) {
		 return userRepo.findById(userId).orElse(null);
	}
	
	public int registerNewUserServiceMethod(String name, String email, String password){
        return userRepo.registerNewUser(name, email, password);
    }
	
	// login
	public List<String> checkUserEmail(String email){
        return userRepo.checkUserEmail(email);
    }
    // End Of Check User Email Services Method.

    public String checkUserPasswordByEmail(String email){
        return userRepo.checkUserPasswordByEmail(email);
    }
    // End Of Check User Password Services Method.

   

}



