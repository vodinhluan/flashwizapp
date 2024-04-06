package com.flashwizserver.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD:FlashWizBE/FlashWizServer/src/main/java/com/flashwizserver/model/AuthApi.java

import com.flashwizserver.security.JWTTokenUtil;

=======

import com.flashwizserver.model.AuthRequest;
import com.flashwizserver.model.AuthResponse;
import com.flashwizserver.model.User;
import com.flashwizserver.security.JWTTokenUtil;

>>>>>>> f51a6747cfc1c55a29c8bbff0539566cfa4f7907:FlashWizBE/FlashWizServer/src/main/java/com/flashwizserver/controller/AuthApi.java
import jakarta.validation.Valid;

@RestController
public class AuthApi {
	@Autowired 
	AuthenticationManager authManager;

	@Autowired
	JWTTokenUtil jwtTokenUtil;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
					);

			User user = (User) authentication.getPrincipal();
<<<<<<< HEAD:FlashWizBE/FlashWizServer/src/main/java/com/flashwizserver/model/AuthApi.java

=======
>>>>>>> f51a6747cfc1c55a29c8bbff0539566cfa4f7907:FlashWizBE/FlashWizServer/src/main/java/com/flashwizserver/controller/AuthApi.java
			
			String accessToken = jwtTokenUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

			return ResponseEntity.ok(response);
			
		} catch(BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	
<<<<<<< HEAD:FlashWizBE/FlashWizServer/src/main/java/com/flashwizserver/model/AuthApi.java
}
=======
}
>>>>>>> f51a6747cfc1c55a29c8bbff0539566cfa4f7907:FlashWizBE/FlashWizServer/src/main/java/com/flashwizserver/controller/AuthApi.java
