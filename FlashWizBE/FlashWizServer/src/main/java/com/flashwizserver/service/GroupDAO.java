package com.flashwizserver.service;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class GroupDAO {	
	public String generateRandomGroupCode() {
        // Tạo một chuỗi ngẫu nhiên dài 6 ký tự
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return stringBuilder.toString();
    }

}
