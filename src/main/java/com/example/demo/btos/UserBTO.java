package com.example.demo.btos;


import java.sql.Date;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import lombok.Data;

@Data
public class UserBTO {

    private String userName;
    private String password;
    private String email;
    private Timestamp passwordUpdateTimestamp;
    private Timestamp lastConnTimestamp;
    private Timestamp dateInscription;
    
    public String generateMD5(String input) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add input string bytes to digest
            md.update(input.getBytes(StandardCharsets.UTF_8));
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception or throw a runtime exception
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }
    
    public Timestamp getDateInscription() {
		long millis = System.currentTimeMillis();
		this.setDateInscription(new Timestamp(millis));
		return this.dateInscription;
    }
    
    public String getPassword() {
    	return this.generateMD5(this.password);
    }
    
    public void trackPasswordChange() {
		long millis = System.currentTimeMillis();
		this.setPasswordUpdateTimestamp(new Timestamp(millis));
    }
}
