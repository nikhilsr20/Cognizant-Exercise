package com.cognizant.librarymanagementsystem;

import com.cognizant.librarymanagementsystem.model.Book;
import com.cognizant.librarymanagementsystem.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class LibraryManagementApplication {

    private static final Logger logger = LoggerFactory.getLogger(LibraryManagementApplication.class);

    public static void main(String[] args) {

        printBanner();

        logger.info("Loading Spring ApplicationContext...");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info("ApplicationContext loaded successfully.");

        // Exercise 2 & 5
        section("Exercise 2 & 5 - Setter Injection");
        BookService setterService = (BookService) context.getBean("bookServiceSetter");
        runDemoOperations(setterService, "Setter Injected BookService");

        // Exercise 6
        section("Exercise 6 - Annotation-based Configuration");
        BookService annotationService = context.getBean(BookService.class);
        logger.info("Annotation-based BookService: {}", annotationService);

        List<Book> annotationBooks = annotationService.getAllBooks();
        annotationBooks.forEach(book -> logger.info("{}", book));

        // Exercise 7
        section("Exercise 7 - Constructor Injection");
        BookService constructorService =
                (BookService) context.getBean("bookServiceConstructor");
        runDemoOperations(constructorService, "Constructor Injected BookService");

        // Exercise 3
        section("Exercise 3 - Spring AOP");
        Book newBook = new Book("B999", "Domain-Driven Design", "Eric Evans");

        setterService.addBook(newBook);
        logger.info("Book: {}", setterService.getBook("B999"));

        setterService.removeBook("B999");
        logger.info("Total Books: {}", setterService.getAllBooks().size());

        ((ClassPathXmlApplicationContext) context).close();
        logger.info("Application finished.");
    }

    private static void runDemoOperations(BookService service, String label) {

        logger.info("----- {} -----", label);

        List<Book> books = service.getAllBooks();
        books.forEach(book -> logger.info("{}", book));

        logger.info("Book: {}", service.getBook("B001"));

        Book newBook = new Book("B100", "Refactoring", "Martin Fowler");
        service.addBook(newBook);

        logger.info("Books after add: {}", service.getAllBooks().size());
    }

    private static void section(String title) {
        logger.info("");
        logger.info("========================================================");
        logger.info(title);
        logger.info("========================================================");
    }

    private static void printBanner() {
        System.out.println();
        System.out.println("========================================================");
        System.out.println("      Library Management System");
        System.out.println("      Spring Core & Maven");
        System.out.println("      Package: com.cognizant.librarymanagementsystem");
        System.out.println("========================================================");
        System.out.println();
    }
}