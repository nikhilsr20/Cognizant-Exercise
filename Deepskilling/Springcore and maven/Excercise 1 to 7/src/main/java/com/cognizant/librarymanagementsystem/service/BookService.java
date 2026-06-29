package com.cognizant.librarymanagementsystem.service;

import com.cognizant.librarymanagementsystem.model.Book;
import com.cognizant.librarymanagementsystem.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BookService - Business logic layer for library operations.

 * Exercise 1  : Defined as a bean in applicationContext.xml; loads BookRepository
 * Exercise 2  : BookRepository injected via SETTER injection (XML wiring)
 * Exercise 5  : Verifies IoC container wires all beans correctly
 * Exercise 6  : @Service annotation enables component scanning
 * Exercise 7  : Demonstrates BOTH constructor injection AND setter injection

 * How Exercise 7 works:
 *   - The XML bean "bookService_constructor" uses <constructor-arg> → constructor injection
 *   - The XML bean "bookService" uses <property name="bookRepository"> → setter injection
 *   - The main class loads BOTH beans and invokes them to compare
 */
@Service  // Exercise 6: Annotation-based configuration
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private BookRepository bookRepository;


    // Exercise 7: Constructor Injection
    // Spring calls this constructor when the XML uses <constructor-arg>

    public BookService(BookRepository bookRepository) {
        logger.info("[BookService] Constructor Injection — BookRepository injected via constructor");
        this.bookRepository = bookRepository;
    }


    // No-arg constructor needed so Spring can also do setter injection
    // (used by the default "bookService" XML bean + @Service scanning)

    public BookService() {
        logger.info("[BookService] No-arg constructor called (setter injection path)");
    }


    // Exercise 2 & 7: Setter Injection
    // Spring calls this setter when the XML uses <property name="bookRepository">

    @Autowired  // Also satisfies Exercise 6 annotation wiring via component scan
    public void setBookRepository(BookRepository bookRepository) {
        logger.info("[BookService] Setter Injection — BookRepository injected via setter");
        this.bookRepository = bookRepository;
    }


    // Business methods (these are intercepted by LoggingAspect — Ex 3)


    /** Add a new book to the library */
    public void addBook(Book book) {
        logger.info("[BookService] addBook() called for: {}", book.getTitle());
        bookRepository.save(book);
        logger.info("[BookService] Book added successfully.");
    }

    /** Retrieve a book by its ID */
    public Book getBook(String id) {
        logger.info("[BookService] getBook() called for id: {}", id);
        Book book = bookRepository.findById(id);
        if (book == null) {
            logger.warn("[BookService] No book found with id: {}", id);
        }
        return book;
    }

    /** List all books in the library */

    public List<Book> getAllBooks() {
        logger.info("[BookService] getAllBooks() called");
        return bookRepository.findAll();
    }

    /** Remove a book by its ID */
    public void removeBook(String id) {
        logger.info("[BookService] removeBook() called for id: {}", id);
        bookRepository.delete(id);
        logger.info("[BookService] Book removed successfully.");
    }
}
