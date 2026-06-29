package com.cognizant.librarymanagementsystem.repository;

import com.cognizant.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookRepository — Spring Data JPA repository for the Book entity.

 * Exercise 8: JpaRepository provides CRUD out of the box.
 * All method calls here are intercepted by LoggingAspect via AOP.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCase(String keyword);
}
