package com.example.demo.entities;


import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.security.Timestamp;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
	@Column(name="user_name", nullable = false, length = 100)
    private String userName;
	@Column(name="password", nullable = false, length = 255)
    private String password;
	@Column(name="email", unique = true, nullable = false, length = 255)
    private String email;
    @Column(name = "date_inscription", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateInscription;
	
}
