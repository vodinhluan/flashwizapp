package com.flashwizserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flashwizserver.model.User;
import com.flashwizserver.service.UserDAO;

@RestController
public class UserController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;

	@GetMapping("/user/get-all")
	public List<User> getAllUsers() {
		return userDAO.listUser();
	}
	
	@PostMapping("/user/save") 
	public void save(@RequestBody User user) {
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		userDAO.saveUser(user);
	}

	
//	 @PostMapping("/user/register")
//	    public ResponseEntity registerNewUser(@RequestParam("name") String name,
//	                                          @RequestParam("email")String email,
//	                                          @RequestParam("password")String password){
//
//	        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
//	            return new ResponseEntity<>("Hãy điền đầy đủ thông tin ", HttpStatus.BAD_REQUEST);
//	        } 	
//
//	        // Encrypt / Hash Password:
//	        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
//
//	        // Register New User:
//	        int result = userDAO.registerNewUserServiceMethod(name, email, hashed_password);
//
//	        if(result != 1){
//	            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
//	        }
//
//	        return new ResponseEntity<>("success", HttpStatus.OK);
//
//	    }
	@PostMapping("/user/register")
	public ResponseEntity registerNewUser(@RequestParam("name") String name,
	                                      @RequestParam("email") String email,
	                                      @RequestParam("password") String password){

	    if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
	        return new ResponseEntity<>("Hãy điền đầy đủ thông tin ", HttpStatus.BAD_REQUEST);
	    }

	    // Encrypt / Hash Password:
	    String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

	    // Register New User:
	    int result = userDAO.registerNewUserServiceMethod(name, email, hashed_password);

	    if(result != 1){
	        return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
	    }

	    // Trả về thông tin người dùng dưới dạng JSON
	    User newUser = new User(email, password, name);
	    return ResponseEntity.ok(newUser);
	}
	
	@GetMapping("/user/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userDAO.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}