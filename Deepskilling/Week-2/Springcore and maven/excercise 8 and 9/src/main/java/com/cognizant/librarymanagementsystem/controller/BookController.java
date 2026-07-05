package com.cognizant.librarymanagementsystem.controller;

import com.cognizant.librarymanagementsystem.model.Book;
import com.cognizant.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Exercise 9 – REST CRUD Endpoints

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String q) {
        return ResponseEntity.ok(bookService.searchByTitle(q));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saved = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody Book book) {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, book));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

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