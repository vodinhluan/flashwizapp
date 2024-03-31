package com.flashwizserver.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.flashwizserver.model.User;

import io.jsonwebtoken.Jwts;

@Component
public class JWTTokenUtil {
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24hours
	
	@Value("${app.jwt.secret}")
	private String secretKey;
	
	public String generateAccessToken(User user) {
		return Jwts.builder()
				.setSubject(user.getId() + "," + user.getEmail())
				.setIssuer("FlashWizBackEnd")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secretKey)
				.compact();		
	}
}
