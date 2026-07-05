package com.cognizant.librarymanagementsystem.repository;

import com.cognizant.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Exercise 9 – Spring Data JPA Repository
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCase(String keyword);
}