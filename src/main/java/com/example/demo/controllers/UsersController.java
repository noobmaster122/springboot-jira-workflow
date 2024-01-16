package com.example.demo.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.btos.BookBTO;
import com.example.demo.dtos.BookDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.services.UsersService;



@RestController
public class UsersController {
	
	@Autowired
	UsersService usersService; 
	
	
	@PostMapping("/save-user")
	public ResponseEntity<?> saveBook(@RequestBody UserDTO userDTO) {
		try {

			UserDTO newUser = usersService.saveUser(userDTO);

			if (newUser != null) {
				return new ResponseEntity<UserDTO>(newUser, HttpStatus.CREATED);
			}

			return new ResponseEntity<String>("Error while saving the user", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<>("Server error while saving the user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/get-users")
	public ResponseEntity<?> allUser() {
		try {

			List<UserDTO> usersList = usersService.getAllUsers();

			if (!usersList.isEmpty()) {
				return new ResponseEntity<List<UserDTO>>(usersList, HttpStatus.OK);
			}

			return new ResponseEntity<String>("Error while saving the user", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<>("Server error while saving the user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
