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
	
	public String getTitle() {
		System.out.println("am returning title");
		return this.title;
	}
}
