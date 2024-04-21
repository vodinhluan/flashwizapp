package com.flashwizserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flashwizserver.security.JWTTokenUtil;
import com.flashwizserver.service.UserDAO;
import com.flashwizserver.model.AuthRequest;
import com.flashwizserver.model.AuthResponse;
import com.flashwizserver.model.User;

import jakarta.validation.Valid;

@RestController
public class AuthApi {
	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JWTTokenUtil jwtTokenUtil;
	@Autowired
	UserDAO userDAO;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

			User user = (User) authentication.getPrincipal();

			String accessToken = jwtTokenUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

			return ResponseEntity.ok(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

   

}
