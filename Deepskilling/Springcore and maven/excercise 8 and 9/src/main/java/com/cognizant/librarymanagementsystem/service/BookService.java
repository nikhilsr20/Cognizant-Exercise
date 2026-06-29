package com.cognizant.librarymanagementsystem.service;

import com.cognizant.librarymanagementsystem.model.Book;
import com.cognizant.librarymanagementsystem.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * BookService — Business logic layer.
 *
 * Exercise 8: Every public method in this class is intercepted by
 * LoggingAspect (@Before, @After, @Around) to demonstrate AOP
 * separation of cross-cutting concerns.
 */
@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // CREATE

    public Book addBook(Book book) {
        logger.info("[BookService] Adding book: {}", book.getTitle());
        return bookRepository.save(book);
    }


    // READ — All

    public List<Book> getAllBooks() {
        logger.info("[BookService] Fetching all books");
        return bookRepository.findAll();
    }


    // READ — By ID

    public Optional<Book> getBookById(Long id) {
        logger.info("[BookService] Fetching book by id: {}", id);
        return bookRepository.findById(id);
    }


    // READ — By Author

    public List<Book> getBooksByAuthor(String author) {
        logger.info("[BookService] Fetching books by author: {}", author);
        return bookRepository.findByAuthor(author);
    }


    // READ — By Title keyword

    public List<Book> searchByTitle(String keyword) {
        logger.info("[BookService] Searching books by title keyword: {}", keyword);
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }


    // UPDATE

    public Book updateBook(Long id, Book updatedBook) {
        logger.info("[BookService] Updating book with id: {}", id);
        return bookRepository.findById(id).map(existing -> {
            existing.setTitle(updatedBook.getTitle());
            existing.setAuthor(updatedBook.getAuthor());
            existing.setIsbn(updatedBook.getIsbn());
            return bookRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }


    // DELETE

    public void deleteBook(Long id) {
        logger.info("[BookService] Deleting book with id: {}", id);
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
