package com.cognizant.librarymanagementsystem;

import com.cognizant.librarymanagementsystem.model.Book;
import com.cognizant.librarymanagementsystem.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DataInitializer — Seeds the H2 database with sample books on startup.
 * Runs after the application context is fully loaded.
 */

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            logger.info(">>> Seeding H2 database with sample books ...");

            repository.save(new Book("Clean Code",           "Robert C. Martin",  "978-0132350884"));
            repository.save(new Book("Effective Java",       "Joshua Bloch",      "978-0134685991"));
            repository.save(new Book("Spring in Action",     "Craig Walls",       "978-1617294945"));
            repository.save(new Book("Design Patterns",      "Gang of Four",      "978-0201633610"));
            repository.save(new Book("The Pragmatic Programmer", "Andy Hunt",     "978-0135957059"));

            logger.info(">>> Database seeded. Total books: {}", repository.count());
        };
    }
}
