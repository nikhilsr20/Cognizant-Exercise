package com.cognizant.librarymanagementsystem.model;

import jakarta.persistence.*;

/**
 * Book — JPA Entity mapped to the BOOK table in H2.

 * Exercise 8: This is the domain entity that the REST controller
 * exposes, and whose service/repository methods are intercepted by
 * the LoggingAspect for AOP demonstration.
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private String isbn;


    // Constructors

    public Book() {}

    public Book(String title, String author, String isbn) {
        this.title  = title;
        this.author = author;
        this.isbn   = isbn;
    }


    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public String getTitle()           { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor()             { return author; }
    public void setAuthor(String author)  { this.author = author; }

    public String getIsbn()            { return isbn; }
    public void setIsbn(String isbn)   { this.isbn = isbn; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "', isbn='" + isbn + "'}";
    }
}
