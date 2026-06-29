package com.cognizant.librarymanagementsystem;

import com.cognizant.librarymanagementsystem.model.Book;
import com.cognizant.librarymanagementsystem.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * LibraryManagementApplication — Main entry point.
 *
 * Demonstrates and tests all 7 exercises in sequence:
 *
 *  Exercise 1 : Load Spring context from applicationContext.xml
 *  Exercise 2 : Verify setter-injected BookService works
 *  Exercise 3 : AOP logging intercepts every service method call
 *  Exercise 4 : Maven project properly compiles with all dependencies
 *  Exercise 5 : IoC container wires all beans; context loaded here
 *  Exercise 6 : Annotation (@Service, @Repository) bean also retrieved
 *  Exercise 7 : Constructor-injected BookService verified separately
 */
public class LibraryManagementApplication {

    private static final Logger logger = LoggerFactory.getLogger(LibraryManagementApplication.class);

    public static void main(String[] args) {

        printBanner();

        // ==============================================================
        // Exercise 1 & 5: Load the Spring ApplicationContext from XML
        // This initialises the IoC container and creates all beans.
        // ==============================================================
        logger.info(">>> Loading Spring ApplicationContext from applicationContext.xml ...");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info(">>> Spring ApplicationContext loaded successfully.\n");

        // ==============================================================
        // Exercise 2 & 5: Retrieve bean wired via SETTER INJECTION
        // ==============================================================
        section("Exercise 2 & 5 — Setter Injection (XML wiring)");
        BookService setterService = (BookService) context.getBean("bookServiceSetter");
        runDemoOperations(setterService, "SETTER-INJECTED BookService");

        // ==============================================================
        // Exercise 6: Retrieve bean wired via ANNOTATION (@Service scan)
        // ==============================================================
        section("Exercise 6 — Annotation-based Configuration (@Service / @Repository)");
        BookService annotationService = context.getBean(BookService.class);
        logger.info("Annotation-based BookService bean retrieved: {}", annotationService);
        List<Book> annotationBooks = annotationService.getAllBooks();
        logger.info("Books via annotation-scanned service:");
        annotationBooks.forEach(b -> logger.info("   -> {}", b));

        // ==============================================================
        // Exercise 7: Retrieve bean wired via CONSTRUCTOR INJECTION
        // ==============================================================
        section("Exercise 7 — Constructor Injection (XML <constructor-arg>)");
        BookService constructorService = (BookService) context.getBean("bookServiceConstructor");
        runDemoOperations(constructorService, "CONSTRUCTOR-INJECTED BookService");

        // ==============================================================
        // Exercise 3: AOP — already firing on every call above.
        // Here we add a book and delete it to show more AOP log lines.
        // ==============================================================
        section("Exercise 3 — AOP Logging (observe timestamps above each method)");
        Book newBook = new Book("B999", "Domain-Driven Design", "Eric Evans");
        logger.info("Adding a new book to trigger AOP advice ...");
        setterService.addBook(newBook);

        logger.info("Fetching the newly added book ...");
        Book fetched = setterService.getBook("B999");
        logger.info("Fetched: {}", fetched);

        logger.info("Removing the book ...");
        setterService.removeBook("B999");
        logger.info("Book removed. Final book count: {}", setterService.getAllBooks().size());

        // Close context
        ((ClassPathXmlApplicationContext) context).close();
        logger.info("\n>>> Application finished. Spring context closed.");
    }

    // ---------------------------------------------------------------
    // Helper: run a standard set of CRUD operations on a service bean
    // ---------------------------------------------------------------
    private static void runDemoOperations(BookService service, String label) {
        logger.info("--- {} ---", label);

        // List all pre-seeded books
        List<Book> books = service.getAllBooks();
        logger.info("All books ({}):", books.size());
        books.forEach(b -> logger.info("   {}", b));

        // Find one by ID
        Book book = service.getBook("B001");
        logger.info("getBook('B001') => {}", book);

        // Add a new book
        Book newBook = new Book("B100", "Refactoring", "Martin Fowler");
        service.addBook(newBook);
        logger.info("After add, total books: {}", service.getAllBooks().size());
    }

    // ---------------------------------------------------------------
    // Console helpers
    // ---------------------------------------------------------------
    private static void section(String title) {
        logger.info("\n");
        logger.info("================================================================");
        logger.info("  {}", title);
        logger.info("================================================================");
    }

    private static void printBanner() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       Library Management System — Spring Core (No Boot)      ║");
        System.out.println("║       Base Package: com.rishbootdev.librarymanagementsystem  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Exercises Covered:                                          ║");
        System.out.println("║   1. Basic Spring Application Setup                          ║");
        System.out.println("║   2. Dependency Injection (Setter)                           ║");
        System.out.println("║   3. AOP Logging with Execution Time                         ║");
        System.out.println("║   4. Maven Project Configuration                             ║");
        System.out.println("║   5. Spring IoC Container Configuration                      ║");
        System.out.println("║   6. Annotation-based Bean Configuration                     ║");
        System.out.println("║   7. Constructor and Setter Injection                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
}
