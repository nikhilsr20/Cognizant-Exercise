# Order Service

Part of **Exercise 1 – User and Order Management System**.

Manages orders placed by users. Before persisting an order, it calls
**User Service** to confirm the referenced user actually exists.

## Tech Stack
- Spring Boot 3.2.5
- Spring Web (REST)
- Spring WebFlux (`WebClient` bean provided as an alternative integration option)
- OpenFeign (primary inter-service call mechanism)
- Spring Data JPA
- MySQL
- Bean Validation
- Lombok

## Package Structure
```
com.cognizant.orderservice
├── controller     # REST endpoints
├── service        # Business logic (interface + impl)
├── repository     # Spring Data JPA repository
├── entity         # JPA entities
├── dto            # UserDTO, OrderRequest
├── client         # UserServiceClient (OpenFeign)
├── config         # WebClient bean (alternative to Feign)
└── exception      # Custom exceptions + global exception handler
```

## Port
`8082`

## Inter-service communication
Order Service talks to User Service in two ways, both wired up so you
can compare them:

1. **OpenFeign** (`UserServiceClient`) – used by `OrderServiceImpl` to
   validate the user before saving an order. Declarative, synchronous.
2. **WebClient** (`WebClientConfig`) – a reactive `WebClient` bean is
   also configured against the same `user-service.url`, demonstrating
   the Spring WebFlux approach mentioned in the exercise requirements.

Both point at:
```yaml
user-service:
  url: http://localhost:8081
```

> User Service must be running on port 8081 before you create an order,
> otherwise order creation fails with a 400 (user not found / gateway error).

## Database
MySQL database `order_db` (auto-created).

## Endpoints

| Method | Path                        | Description                  |
|--------|-----------------------------|-------------------------------|
| POST   | `/api/orders`               | Create an order (validates user) |
| GET    | `/api/orders`               | List all orders               |
| GET    | `/api/orders/{id}`          | Get order by id               |
| GET    | `/api/orders/user/{userId}` | Get all orders for a user     |
| DELETE | `/api/orders/{id}`          | Delete an order                |

### Sample request
```http
POST /api/orders
Content-Type: application/json

{
  "userId": 1,
  "product": "Wireless Mouse",
  "quantity": 2,
  "totalAmount": 999.00
}
```

### Sample response
```json
{
  "id": 1,
  "userId": 1,
  "product": "Wireless Mouse",
  "quantity": 2,
  "totalAmount": 999.00,
  "orderDate": "2026-07-04T10:15:30"
}
```

## Running locally
```bash
# 1. Start user-service first (port 8081)
# 2. Then:
cd order-service
mvn spring-boot:run
```

The service starts on `http://localhost:8082`.
