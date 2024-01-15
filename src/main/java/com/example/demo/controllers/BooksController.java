package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.BookDTO;
import com.example.demo.entities.Book;
import com.example.demo.interfaces.BookDtoEntityMapper;
import com.example.demo.services.BookService;

@RestController
public class BooksController {

	@Autowired
	BookDtoEntityMapper BookDtoEntityMapperObj;

	@Autowired
	BookService bookService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

    @PostMapping("/add-book")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        Book bookToAdd = BookDtoEntityMapperObj.toEntity(bookDTO);
        Book savedBook = bookService.addBook(bookToAdd);

        if (savedBook != null) {
            BookDTO savedBookDTO = BookDtoEntityMapperObj.toDto(savedBook);
            return new ResponseEntity<>(savedBookDTO, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
