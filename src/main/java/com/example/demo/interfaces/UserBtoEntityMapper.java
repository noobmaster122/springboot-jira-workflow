package com.example.demo.interfaces;

import org.mapstruct.Mapper;

import com.example.demo.btos.UserBTO;
import com.example.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserBtoEntityMapper {
	UserBTO toBto(User user);
	User toEntity(UserBTO userBto);
}
