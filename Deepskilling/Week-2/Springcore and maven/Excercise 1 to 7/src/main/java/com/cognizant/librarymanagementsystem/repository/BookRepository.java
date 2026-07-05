package com.cognizant.librarymanagementsystem.repository;

import com.cognizant.librarymanagementsystem.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Exercise 6 – Repository Bean
@Repository
public class BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);

    private final Map<String, Book> bookStore = new HashMap<>();

    public BookRepository() {
        logger.info("[BookRepository] Bean instantiated by Spring IoC Container");

        bookStore.put("B001", new Book("B001", "Clean Code", "Robert C. Martin"));
        bookStore.put("B002", new Book("B002", "Effective Java", "Joshua Bloch"));
        bookStore.put("B003", new Book("B003", "Spring in Action", "Craig Walls"));
    }

    // Exercise 1, 2, 5 & 7 – Repository Operations

    public void save(Book book) {
        logger.info("[BookRepository] Saving book: {}", book);
        bookStore.put(book.getId(), book);
    }

    public Book findById(String id) {
        logger.info("[BookRepository] Finding book by id: {}", id);
        return bookStore.get(id);
    }

    public List<Book> findAll() {
        logger.info("[BookRepository] Fetching all books. Count = {}", bookStore.size());
        return new ArrayList<>(bookStore.values());
    }

    public void delete(String id) {
        logger.info("[BookRepository] Deleting book with id: {}", id);
        bookStore.remove(id);
    }
}