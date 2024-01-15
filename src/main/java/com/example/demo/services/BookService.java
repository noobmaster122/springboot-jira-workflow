package com.example.demo.services;

import java.sql.Date;
import java.time.LocalTime; // import the LocalTime class


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.BookRepository;
import com.example.demo.entities.Book;


@Service
public class BookService {

	@Autowired
	BookRepository bookRepo;
	
	public Book addBook(Book book) {
        long millis=System.currentTimeMillis();  
		book.setDatePublication(new Date(millis));
		return bookRepo.save(book);
	}
}
