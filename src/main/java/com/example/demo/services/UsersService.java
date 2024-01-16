package com.example.demo.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.BookDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.interfaces.UserDtoEntityMapper;
import com.example.demo.repositories.UserRepository;

@Service
public class UsersService {
	
	@Autowired
	UserDtoEntityMapper UserDtoEntityMapperObj;
	@Autowired
	UserRepository userRepo;
	
	
	public UserDTO saveUser(UserDTO userDto) {
		
		User user = UserDtoEntityMapperObj.toEntity(userDto);
		long millis = System.currentTimeMillis();
		user.setDateInscription(new Timestamp(millis));
		
		return UserDtoEntityMapperObj.toDto(userRepo.save(user));
	}
	
	public List<UserDTO> getAllUsers() {
		List<User> usersList = userRepo.findAll();
		return usersList.stream().map(UserDtoEntityMapperObj::toDto)
				.collect(Collectors.toList());
	}

}
