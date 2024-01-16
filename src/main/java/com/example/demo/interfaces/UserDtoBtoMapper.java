package com.example.demo.interfaces;

import org.mapstruct.Mapper;

import com.example.demo.btos.UserBTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserDtoBtoMapper {
	UserDTO toDto(UserBTO userBto);
	UserBTO toBto(UserDTO userDto);
}
