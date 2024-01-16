package com.example.demo.services;

import java.sql.Date;
import java.time.LocalTime; // import the LocalTime class

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.BookRepository;
import com.example.demo.btos.BookBTO;
import com.example.demo.entities.Book;
import com.example.demo.interfaces.BookBtoEntityMapper;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepo;
	@Autowired
	BookBtoEntityMapper bookBtoEntityMapper;

	public BookBTO saveBook(BookBTO book) {
		Book bookEnt = bookBtoEntityMapper.toEntity(book);
		long millis = System.currentTimeMillis();
		bookEnt.setDatePublication(new Date(millis));
		
		BookBTO dataToReturn = bookBtoEntityMapper.toBto(bookRepo.save(bookEnt));
		dataToReturn.broadcastInfo();
		return dataToReturn;
	}
	
	public BookBTO updateBook(String isbn, BookBTO updatedBook) {
		
        // Check if the book with the given ISBN exists
        Book existingBook = bookRepo.findByIsbn(isbn);

        if (existingBook != null) {
        	
        	//update title and stock if changed
        	if(updatedBook.getTitle() != null) existingBook.setTitle(updatedBook.getTitle());
        	if(updatedBook.getStock() != null) existingBook.setStock(updatedBook.getStock());

            // Save the updated book
            Book savedBook = bookRepo.save(existingBook);
            // Convert the saved book back to BTO and return it
            return bookBtoEntityMapper.toBto(savedBook);
        }else {
        	return null;
        }
	}
}
