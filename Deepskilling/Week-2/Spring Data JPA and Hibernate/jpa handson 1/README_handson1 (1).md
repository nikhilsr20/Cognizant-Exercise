# Spring Data JPA — Hands-on 1

**Organization:** Cognizant
**Module:** Spring Data JPA and Hibernate
**Topic:** Spring Data JPA Quick Example

---

## Objective

Set up a Spring Boot project with Spring Data JPA and perform a basic database read operation on a `country` table using the repository pattern.

---

## What Was Done

- Created a Spring Boot project using Spring Initializr
- Added dependencies: Spring Data JPA, MySQL Driver, Spring Boot DevTools
- Created the `Country` entity mapped to the `country` table
- Created `CountryRepository` extending `JpaRepository`
- Created `CountryService` with a `getAllCountries()` method
- Called the service from `OrmLearnApplication.main()` and logged the result

---



## How to Run

```bash
mvn spring-boot:run
```

Check the console for log output. You should see all countries printed via `LOGGER.debug`.

---

## Expected Output (Console)

```
INFO  - Inside main
INFO  - Start
DEBUG - countries=[Country{code='IN', name='India'}, Country{code='US', name='United States of America'}, ...]
INFO  - End
```

---

## Key Annotations Used

| Annotation | Purpose |
|------------|---------|
| `@SpringBootApplication` | Marks the main class; enables auto-configuration and component scan |
| `@Entity` | Maps the Java class to a database table |
| `@Table(name="country")` | Specifies the exact table name in the database |
| `@Id` | Marks the primary key field |
| `@Column(name="co_code")` | Maps the field to the exact column name |
| `@Repository` | Marks the interface as a Spring Data repository |
| `@Service` | Marks the class as a business logic component |
| `@Transactional` | Spring manages the database session and transaction automatically |

---

## Concept Summary

`JpaRepository<Country, String>` provides these methods out of the box — no SQL required:

| Method | SQL Equivalent |
|--------|---------------|
| `findAll()` | `SELECT * FROM country` |
| `findById(code)` | `SELECT * FROM country WHERE co_code = ?` |
| `save(country)` | `INSERT` or `UPDATE` |
| `deleteById(code)` | `DELETE FROM country WHERE co_code = ?` |

---

*Cognizant Training Program — Spring Data JPA Module*
