package com.example.demo.dtos;

import java.util.Date;

import com.example.demo.entities.Book;

import lombok.Data;

@Data
public class BookDTO {
	private String title;
	private String isbn;
	private Integer stock;
}
