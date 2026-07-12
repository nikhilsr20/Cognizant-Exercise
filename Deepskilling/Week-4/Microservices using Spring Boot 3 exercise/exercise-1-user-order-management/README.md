# Exercise 1 – User & Order Management System

A **Spring Boot 3** microservices application demonstrating **REST-based synchronous inter-service communication** using **OpenFeign** (with a **WebClient** alternative) and **MySQL**.

## Services

| Service | Port | Description |
|----------|------|-------------|
| User Service | **8081** | Manages user information |
| Order Service | **8082** | Manages orders and validates users through User Service |

## Architecture

```text
Client
   │
   ▼
Order Service ─────────► User Service
     │                       │
     ▼                       ▼
 order_db (MySQL)      user_db (MySQL)
```

## Inter-Service Communication

In a microservices architecture, each service owns its own business logic and database. Services communicate with one another through **HTTP REST APIs**, allowing them to remain **loosely coupled**, independently deployable, and easier to maintain.

This exercise demonstrates **synchronous communication**, where the **Order Service** validates a user by invoking the **User Service** before persisting an order. The communication can be implemented using any of the following Spring technologies:

- **OpenFeign** – A declarative HTTP client that simplifies service-to-service communication by defining Java interfaces instead of writing request-handling code.
- **WebClient** – Spring's modern, non-blocking and reactive HTTP client capable of asynchronous communication while also supporting synchronous calls using `block()`.
- **RestClient** – The modern synchronous HTTP client introduced in Spring Framework 6, designed as the successor to `RestTemplate` with a cleaner and more fluent API.

The communication flow implemented in this exercise is illustrated below.

<p align="center">
  <img src="images/output.png" alt="Inter-Service Communication" width="900">
</p>

### Communication Flow

1. Client sends **POST `/api/orders`**.
2. Order Service receives the request.
3. Order Service invokes **User Service** using **OpenFeign** (or **WebClient/RestClient**).
4. User Service validates the user and returns the response.
5. If the user exists, Order Service stores the order in **MySQL**.
6. The final response is returned to the client.

## Implementation

- Spring Boot 3
- Spring Data JPA
- MySQL
- OpenFeign
- WebClient
- REST APIs
- Layered Architecture

## Run

```bash
# Terminal 1
cd user-service
mvn spring-boot:run

# Terminal 2
cd order-service
mvn spring-boot:run
```