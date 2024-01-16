package com.example.demo.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.btos.UserBTO;
import com.example.demo.dtos.BookDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.Book;
import com.example.demo.entities.User;
import com.example.demo.interfaces.UserBtoEntityMapper;
import com.example.demo.interfaces.UserDtoEntityMapper;
import com.example.demo.repositories.UserRepository;

@Service
public class UsersService {
	
	@Autowired
	UserDtoEntityMapper UserDtoEntityMapperObj;
	@Autowired
	UserBtoEntityMapper userBtoEntMapperObj;
	@Autowired
	UserRepository userRepo;
	
	
	public UserBTO saveUser(UserBTO userBto) {
		
		User user = userBtoEntMapperObj.toEntity(userBto);
		
		return userBtoEntMapperObj.toBto(userRepo.save(user));
	}
	//updateUser
	public UserBTO updateUser(String userName, UserBTO userBto) {
		
		//timestamp for password change
		userBto.trackPasswordChange();
		User newUser = userBtoEntMapperObj.toEntity(userBto);
		//
		User existingUser = userRepo.findByUserName(userName);

        if (existingUser != null) {
        	
        	//update title and stock if changed
        	if(existingUser.getPassword() != null) existingUser.setPassword(newUser.getPassword());
        	//
        	existingUser.setPasswordUpdateTimestamp(newUser.getPasswordUpdateTimestamp());
            // Save the updated book
            User savedUser = userRepo.save(existingUser);
            // Convert the saved book back to BTO and return it
            return userBtoEntMapperObj.toBto(savedUser);
        }else {
        	return null;
        }
	}
	
	public List<UserDTO> getAllUsers() {
		List<User> usersList = userRepo.findAll();
		return usersList.stream().map(UserDtoEntityMapperObj::toDto)
				.collect(Collectors.toList());
	}
	
	public boolean deleteBookByUserName(String userName) {
        Optional<User> optionalUser = Optional.ofNullable(userRepo.findByUserName(userName));

        if (optionalUser.isPresent()) {
        	userRepo.delete(optionalUser.get());
            return true;
        } else {
            return false; // user not found
        }
	}

}
