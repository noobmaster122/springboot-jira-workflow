package com.example.demo.interfaces;

import com.example.demo.dtos.BookDTO;


import com.example.demo.entities.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component // Add this annotation
public interface BookDtoEntityMapper {
	BookDTO toDto(Book book);
	Book toEntity(BookDTO bookDTO);
}
