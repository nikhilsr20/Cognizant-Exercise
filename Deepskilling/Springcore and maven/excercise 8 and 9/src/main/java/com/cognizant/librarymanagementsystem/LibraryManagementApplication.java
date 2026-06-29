package com.cognizant.librarymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LibraryManagementApplication — Spring Boot entry point.

 * Exercise 8: Basic AOP with Spring Boot
 *
 * @SpringBootApplication combines:
 *   @Configuration       — marks this as a configuration class
 *   @EnableAutoConfiguration — auto-configures Spring Boot features
 *   @ComponentScan       — scans com.rishbootdev.librarymanagementsystem.*

 * AOP is auto-enabled by spring-boot-starter-aop (no @EnableAspectJAutoProxy needed).

 * Run:  mvn spring-boot:run
 * Test: http://localhost:8080/api/books
 * H2:   http://localhost:8080/h2-console
 */
@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }
}
