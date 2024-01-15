package com.example.demo.btos;

import java.util.Date;

import com.example.demo.entities.Book;

import lombok.Data;

@Data
public class BookBTO {
	private String title;
	private String isbn;
	private Date datePublication;
	private Integer stock;
	
	public void broadcastInfo() {
		System.out.println("any time a book is added, all users should receive a notification informing them that a new book has dropped...");
	}
}
