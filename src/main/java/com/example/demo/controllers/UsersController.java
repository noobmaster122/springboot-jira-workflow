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
import com.example.demo.btos.UserBTO;
import com.example.demo.dtos.BookDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.interfaces.UserDtoBtoMapper;
import com.example.demo.services.UsersService;

import jakarta.websocket.server.PathParam;



@RestController
public class UsersController {
	
	@Autowired
	UsersService usersService; 
	@Autowired
	UserDtoBtoMapper userDtoBtoMapperObj;
	
	
	@PostMapping("/save-user")
	public ResponseEntity<?> saveBook(@RequestBody UserDTO userDTO) {
		try {

			UserBTO userBto = userDtoBtoMapperObj.toBto(userDTO);
			UserBTO newUser = usersService.saveUser(userBto);

			if (newUser != null) {
				return new ResponseEntity<UserDTO>(userDtoBtoMapperObj.toDto(newUser), HttpStatus.CREATED);
			}

			return new ResponseEntity<String>("Error while saving the user", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<>("Server error while saving the user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/update-user/{userName}")
	public ResponseEntity<?> saveBook(@PathVariable String userName, @RequestBody UserDTO userDTO) {
		try {
			
			UserBTO userBto = userDtoBtoMapperObj.toBto(userDTO);

			UserBTO updatedUserBto = usersService.updateUser(userName, userBto);

			if (updatedUserBto != null) {
				// Convert the updated user to DTO and return it
				UserDTO updatedUserDto = userDtoBtoMapperObj.toDto(updatedUserBto);
				return new ResponseEntity<UserDTO>(updatedUserDto, HttpStatus.OK);
			} else {
				// Book with the given ISBN not found
				return new ResponseEntity<String>("user not found", HttpStatus.NOT_FOUND);
			}
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
	@DeleteMapping("/delete-user/{userName}")
	public ResponseEntity<String> deleteBook(@PathVariable String userName) {
		try {
			boolean deleted = usersService.deleteBookByUserName(userName);

			if (deleted) {
				return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<>("Error deleting the User.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
