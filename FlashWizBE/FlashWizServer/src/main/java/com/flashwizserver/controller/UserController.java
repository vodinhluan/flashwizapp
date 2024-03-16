package com.flashwizserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/user/get-all")
	public List<User> getAllUsers() {
		return userDAO.listUser();
	}

	 @PostMapping("/user/save")
	    public ResponseEntity<String> save(@RequestBody User user) {
	        // Kiểm tra và lưu thông tin người dùng
	        try {
	            userDAO.saveUser(user);
	            // Trả về phản hồi thành công nếu lưu thành công
	            return ResponseEntity.ok("success");
	        } catch (Exception e) {
	            // Trả về phản hồi lỗi nếu có lỗi xảy ra
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error message: " + e.getMessage());
	        }
	    }
	
//	@PostMapping("/user/register")
//    public ResponseEntity registerNewUser(@RequestParam("name") String name,
//                                          @RequestParam("email")String email,
//                                          @RequestParam("password")String password){
//
//        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
//            return new ResponseEntity<>("Hãy điền đầy đủ thông tin ", HttpStatus.BAD_REQUEST);
//        }
//
//        // Encrypt / Hash Password:
//        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
//
//        // Register New User:
//        int result = userDAO.registerNewUserServiceMethod(name, email, hashed_password);
//
//        if(result != 1){
//            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>("success", HttpStatus.OK);
//
//    }
}