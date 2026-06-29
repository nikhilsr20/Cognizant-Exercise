package com.cognizant.librarymanagementsystem.controller;

import com.cognizant.librarymanagementsystem.model.Book;
import com.cognizant.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController — REST Controller exposing CRUD endpoints for Book.
 *
 * Exercise 8: Every endpoint call is intercepted by LoggingAspect
 * (@Before logs the incoming request, @After logs the exit).
 *
 * Base URL: http://localhost:8080/api/books
 *
 * Endpoints:
 *   GET    /api/books              → getAllBooks()
 *   GET    /api/books/{id}         → getBookById()
 *   GET    /api/books/author/{a}   → getBooksByAuthor()
 *   GET    /api/books/search?q=    → searchByTitle()
 *   POST   /api/books              → addBook()
 *   PUT    /api/books/{id}         → updateBook()
 *   DELETE /api/books/{id}         → deleteBook()
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    // GET /api/books — Retrieve all books


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    // GET /api/books/{id} — Retrieve a single book

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // GET /api/books/author/{author} — Filter by author


    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }


    // GET /api/books/search?q=keyword — Search by title keyword


    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String q) {
        return ResponseEntity.ok(bookService.searchByTitle(q));
    }


    // POST /api/books — Add a new book

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saved = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    // PUT /api/books/{id} — Update an existing book

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody Book book) {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, book));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }


    // DELETE /api/books/{id} — Delete a book

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
