# Customer Service

Part of **Exercise 3 – API Gateway**.

A simple CRUD service, routed to by the API Gateway at `/customers/**`.
Uses an in-memory H2 database so the exercise can be run standalone
without any external database setup.

## Tech Stack
- Spring Boot 3.2.5
- Spring Web (REST)
- Spring Data JPA + H2 (in-memory)
- Lombok

## Port
`8085`

## Endpoints (direct)

| Method | Path                    | Description        |
|--------|-------------------------|----------------------|
| POST   | `/api/customers`        | Create a customer     |
| GET    | `/api/customers`        | List all customers     |
| GET    | `/api/customers/{id}`   | Get customer by id      |

## Through the API Gateway
The gateway rewrites `/customers/**` to `/api/customers/**`, so from
the outside you'd call:
```
GET http://localhost:8080/customers
```
instead of hitting this service directly on `8085`.

## Running locally
```bash
cd customer-service
mvn spring-boot:run
```

H2 console (optional, for inspecting data): `http://localhost:8085/h2-console`
(JDBC URL: `jdbc:h2:mem:customerdb`, user: `sa`, no password).
