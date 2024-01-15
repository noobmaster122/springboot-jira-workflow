package com.example.demo.interfaces;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.btos.BookBTO;
import com.example.demo.entities.Book;

@Mapper(componentModel = "spring")
@Component // Add this annotation
public interface BookBtoEntityMapper {
	BookBTO toBto(Book book);
	Book toEntity(BookBTO bookBTO);
}
