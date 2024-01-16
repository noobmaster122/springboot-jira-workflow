package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@DeleteMapping("/delete-book/{isbn}")
	public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
		try {
			boolean deleted = bookService.deleteBookByIsbn(isbn);

			if (deleted) {
				return new ResponseEntity<>("Book deleted successfully.", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<>("Error deleting the book.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-books")
	public ResponseEntity<?> getBooks() {
		try {
			List<Book> allBooks = bookService.getAllBooks();

			if (!allBooks.isEmpty()) {
				List<BookDTO> allBooksDTO = allBooks.stream().map(bookDtoEntityMapperObj::toDto)
						.collect(Collectors.toList());
				return new ResponseEntity<List<BookDTO>>(allBooksDTO, HttpStatus.OK);
			}

			return new ResponseEntity<String>("No books found.", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<String>("Error while trying to get books.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save-book")
	public ResponseEntity<?> saveBook(@RequestBody BookDTO bookDTO) {
		try {
			BookBTO bookToAdd = bookBtoDtoMapperObj.toBto(bookDTO);

			BookBTO savedBook = bookService.saveBook(bookToAdd);

			if (savedBook != null) {
				BookDTO savedBookDTO = bookBtoDtoMapperObj.toDto(savedBook);
				return new ResponseEntity<BookDTO>(savedBookDTO, HttpStatus.CREATED);
			}

			return new ResponseEntity<String>("Error while saving the book", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<>("Server error while saving the book", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update-book/{isbn}")
	public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody BookDTO updatedBookDTO) {

		try {
			BookBTO bookToAdd = bookBtoDtoMapperObj.toBto(updatedBookDTO);

			BookBTO updatedBookBTO = bookService.updateBook(isbn, bookToAdd);

			if (updatedBookBTO != null) {
				// Convert the updated book to DTO and return it
				BookDTO bookToReturnDTO = bookBtoDtoMapperObj.toDto(updatedBookBTO);
				return new ResponseEntity<BookDTO>(bookToReturnDTO, HttpStatus.OK);
			} else {
				// Book with the given ISBN not found
				return new ResponseEntity<String>("Book not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<String>("Server error while modifying the book data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
