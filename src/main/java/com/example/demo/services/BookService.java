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
}
