package com.example.demo.interfaces;

import org.mapstruct.Mapper;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserDtoEntityMapper {
	UserDTO toDto(User user);
	User toEntity(UserDTO userDto);
}
