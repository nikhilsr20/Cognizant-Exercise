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

## Dependencies Added (pom.xml)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

---

## Database Setup

```sql
CREATE SCHEMA ormlearn;
USE ormlearn;

CREATE TABLE country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50)
);

INSERT INTO country VALUES ('IN', 'India');
INSERT INTO country VALUES ('US', 'United States of America');
```

---

## application.properties

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ormlearn
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update

logging.level.org.springframework=info
logging.level.com.cognizant=debug
logging.level.org.hibernate.SQL=trace
```

---

## Files Created

### Country.java
```java
package com.cognizant.ormlearn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "co_code")
    private String code;

    @Column(name = "co_name")
    private String name;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Country{code='" + code + "', name='" + name + "'}";
    }
}
```

### CountryRepository.java
```java
package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
```

### CountryService.java
```java
package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
```

### OrmLearnApplication.java
```java
package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        LOGGER.info("Inside main");
        testGetAllCountries();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }
}
```

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
