package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.btos.BookBTO;
import com.example.demo.dtos.BookDTO;
import com.example.demo.entities.Book;
import com.example.demo.interfaces.BookBtoDtoMapper;
import com.example.demo.interfaces.BookBtoEntityMapper;
import com.example.demo.interfaces.BookDtoEntityMapper;
import com.example.demo.services.BookService;

@RestController
public class BooksController {

	@Autowired
	BookDtoEntityMapper bookDtoEntityMapperObj;
	@Autowired
	BookBtoDtoMapper bookBtoDtoMapperObj;
	@Autowired
	BookBtoEntityMapper bookBtoEntityManager;

	@Autowired
	BookService bookService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@PostMapping("/save-book")
	public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
		BookBTO bookToAdd = bookBtoDtoMapperObj.toBto(bookDTO);

		BookBTO savedBook = bookService.saveBook(bookToAdd);

		if (savedBook != null) {
			BookDTO savedBookDTO = bookBtoDtoMapperObj.toDto(savedBook);
			return new ResponseEntity<>(savedBookDTO, HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/update-book/{isbn}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable String isbn,@RequestBody BookDTO updatedBookDTO) {
		
		BookBTO bookToAdd = bookBtoDtoMapperObj.toBto(updatedBookDTO);

		BookBTO updatedBookBTO = bookService.updateBook(isbn, bookToAdd);

        if (updatedBookBTO != null) {
            // Convert the updated book to DTO and return it
            BookDTO bookToReturnDTO = bookBtoDtoMapperObj.toDto(updatedBookBTO);
            return ResponseEntity.ok(bookToReturnDTO);
        } else {
            // Book with the given ISBN not found
            return ResponseEntity.notFound().build();
        }
	}

}
