package com.inventory.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.entity.Book;
import com.inventory.service.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookRepository bookRepository;

	/*
	 * URL : http://localhost:8081/api/books/1
	 * Get Operation
	 */

	@GetMapping("/{id}")  // ← This matches what Order Service expects
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
	    Book book = bookRepository.findById(id).orElseThrow();
	    return ResponseEntity.ok(book);
	}

	/*
	 * URL : http://localhost:8081/api/books/save-book {"title":
	 * "Spring Boot Guide", "author": "John Doe", "stockQuantity": 50 }
	 * Post Operation
	 */
	@PostMapping("/save-book")
	public Book addBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	/*
	 * URL : http://localhost:8081/api/books/1?quantity=52
	 * Put Operation
	 */

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateStock(@PathVariable Long id, @RequestParam int quantity) {
		Book book = bookRepository.findById(id).orElseThrow();
		book.setStockQuantity(quantity);
		return ResponseEntity.ok(bookRepository.save(book));
	}
	
	
	//Delete Operation
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id) {
	    if (bookRepository.existsById(id)) {
	        bookRepository.deleteById(id);
	        return ResponseEntity.ok("Book deleted successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
	    }
	}

}