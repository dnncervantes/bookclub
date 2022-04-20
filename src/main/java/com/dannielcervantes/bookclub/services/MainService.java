package com.dannielcervantes.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dannielcervantes.bookclub.models.Book;
import com.dannielcervantes.bookclub.repositories.BookRepository;

@Service
public class MainService {
	@Autowired
	private BookRepository bookRepo;
	
	// Find all Books
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	
	// Find one Book Id
	public Book oneBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	// Create Book
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}
	// Update Book
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}
	// Delete
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
}
