package com.cognizant.librarymanagementsystem.repository;

import com.cognizant.librarymanagementsystem.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BookRepository - Data access layer for books.
 *
 * Exercise 1  : Defined as a bean in applicationContext.xml
 * Exercise 2  : Injected into BookService via setter DI
 * Exercise 5  : Re-confirmed in IoC container config
 * Exercise 6  : @Repository annotation replaces XML bean definition
 * Exercise 7  : Remains target of both constructor and setter injection
 */
@Repository  // Exercise 6: Annotation-based configuration
public class BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);

    // In-memory store simulating a database
    private final Map<String, Book> bookStore = new HashMap<>();

    public BookRepository() {
        logger.info("[BookRepository] Bean instantiated by Spring IoC Container");
        // Seed some default data
        bookStore.put("B001", new Book("B001", "Clean Code",         "Robert C. Martin"));
        bookStore.put("B002", new Book("B002", "Effective Java",     "Joshua Bloch"));
        bookStore.put("B003", new Book("B003", "Spring in Action",   "Craig Walls"));
    }

    /** Save or update a book */
    public void save(Book book) {
        logger.info("[BookRepository] Saving book: {}", book);
        bookStore.put(book.getId(), book);
    }

    /** Find a book by ID */
    public Book findById(String id) {
        logger.info("[BookRepository] Finding book by id: {}", id);
        return bookStore.get(id);
    }

    /** Return all books */
    public List<Book> findAll() {
        logger.info("[BookRepository] Fetching all books. Count = {}", bookStore.size());
        return new ArrayList<>(bookStore.values());
    }

    /** Delete a book by ID */
    public void delete(String id) {
        logger.info("[BookRepository] Deleting book with id: {}", id);
        bookStore.remove(id);
    }
}
