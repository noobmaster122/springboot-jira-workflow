package com.example.demo.entities;

import java.security.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Book")
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title", nullable = false, length = 255)
	private String title;
	
	@Column(name = "isbn", unique = true, nullable = false, length = 50)
	private String isbn;
	
	@Column(name = "date_publication", nullable = false, updatable = false)
	private Date datePublication;
	
	@Column(name = "stock", nullable = false, columnDefinition = "INT DEFAULT 0")
	private Integer stock;
}
