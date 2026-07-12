# Billing Service

Part of **Exercise 3 – API Gateway**.

A simple CRUD service for customer bills, routed to by the API Gateway
at `/billing/**`. Uses an in-memory H2 database.

## Tech Stack
- Spring Boot 3.2.5
- Spring Web (REST)
- Spring Data JPA + H2 (in-memory)
- Lombok

## Port
`8086`

## Endpoints (direct)

| Method | Path                              | Description               |
|--------|------------------------------------|-----------------------------|
| POST   | `/api/bills`                       | Create a bill                |
| GET    | `/api/bills`                       | List all bills                |
| GET    | `/api/bills/{id}`                  | Get bill by id                |
| GET    | `/api/bills/customer/{customerId}` | Get all bills for a customer  |

## Through the API Gateway
The gateway rewrites `/billing/**` to `/api/bills/**`, so from the
outside you'd call:
```
GET http://localhost:8080/billing
```
instead of hitting this service directly on `8086`.

## Running locally
```bash
cd billing-service
mvn spring-boot:run
```

H2 console (optional): `http://localhost:8086/h2-console`
(JDBC URL: `jdbc:h2:mem:billingdb`, user: `sa`, no password).
