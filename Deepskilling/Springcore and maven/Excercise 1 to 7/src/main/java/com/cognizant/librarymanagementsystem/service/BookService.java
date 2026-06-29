package com.cognizant.librarymanagementsystem.service;

import com.cognizant.librarymanagementsystem.model.Book;
import com.cognizant.librarymanagementsystem.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Exercise 6 – Service Bean
@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private BookRepository bookRepository;

    // Exercise 7 – Constructor Injection
    public BookService(BookRepository bookRepository) {
        logger.info("[BookService] Constructor Injection");
        this.bookRepository = bookRepository;
    }

    public BookService() {
        logger.info("[BookService] Default Constructor");
    }

    // Exercise 2 – Setter Injection
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        logger.info("[BookService] Setter Injection");
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        logger.info("[BookService] Adding book: {}", book.getTitle());
        bookRepository.save(book);
    }

    public Book getBook(String id) {
        logger.info("[BookService] Fetching book: {}", id);

        Book book = bookRepository.findById(id);

        if (book == null) {
            logger.warn("[BookService] Book not found: {}", id);
        }

        return book;
    }

    public List<Book> getAllBooks() {
        logger.info("[BookService] Fetching all books");
        return bookRepository.findAll();
    }

    public void removeBook(String id) {
        logger.info("[BookService] Removing book: {}", id);
        bookRepository.delete(id);
    }
}