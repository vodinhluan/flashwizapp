package com.flashwizserver.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	public List<String> checkUserEmail(String email){
        return userRepo.checkUserEmail(email);
    }

    public String checkUserPasswordByEmail(String email){
        return userRepo.checkUserPasswordByEmail(email);
    }

    
    public void updateResetPasswordOTP(String OTP, String email) throws AccountNotFoundException {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setResetPasswordOTP(OTP);
            userRepo.save(user);
        } else {
            throw new AccountNotFoundException("Could not find any user with the email " + email);
        }
    }
    public Optional<User> getByResetPasswordOTP(String OTP) {
        return userRepo.findByResetPasswordOTP(OTP);
    }
     
    public boolean updatePassword(Optional<User> userOptional, String newPassword) {
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            user.setResetPasswordOTP(null); // Cập nhật OTP thành null sau khi đã sử dụng nó để reset mật khẩu
            userRepo.save(user);
            return true; // Cập nhật mật khẩu thành công
        } else {
            return false; // Không tìm thấy User
        }
    }

	public Integer getUserIdByEmail(String userEmail) {
		return userRepo.findUserIdByEmail(userEmail);
	}

	 public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElse(null); 
    }

	 public User getUserById(Integer id) {
	        Optional<User> userOptional = userRepo.findById(id);
	        return userOptional.orElse(null);
	    }
  

}



