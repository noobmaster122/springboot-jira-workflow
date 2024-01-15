package com.example.demo.interfaces;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.btos.BookBTO;
import com.example.demo.dtos.BookDTO;

@Mapper(componentModel = "spring")
@Component // Add this annotation
public interface BookBtoDtoMapper {
	BookBTO toBto(BookDTO BookDTO);
	BookDTO toDto(BookBTO bookBTO);
}
