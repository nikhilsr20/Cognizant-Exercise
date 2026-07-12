# Product Service

Part of **Exercise 2 – Inventory Management System with Service Discovery**.

Manages the product catalog and stock counts. Registers itself with
Eureka and pulls its configuration from Config Server.

## Tech Stack
- Spring Boot 3.2.5
- Spring Web (REST)
- Spring Data JPA + MySQL
- Spring Cloud Netflix Eureka Client
- Spring Cloud Config Client
- Lombok

## Package Structure
```
com.cognizant.productservice
├── controller
├── service
├── repository
├── entity
└── exception
```

## Port
`8083` (pulled from Config Server's `product-service.yml`)

## Service Discovery & Config
```yaml
spring:
  config:
    import: optional:configserver:http://localhost:8888
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

## Endpoints

| Method | Path                          | Description         |
|--------|-------------------------------|----------------------|
| POST   | `/api/products`               | Create a product      |
| GET    | `/api/products`               | List all products     |
| GET    | `/api/products/{id}`          | Get product by id      |
| PATCH  | `/api/products/{id}/stock?stock=N` | Update stock count |
| DELETE | `/api/products/{id}`          | Delete a product        |

### Sample request
```http
POST /api/products
Content-Type: application/json

{
  "name": "Mechanical Keyboard",
  "description": "RGB backlit, hot-swappable switches",
  "price": 3499.00,
  "stock": 50
}
```

## Running locally
Start order matters:
```bash
# 1. eureka-server   (8761)
# 2. config-server    (8888)
# 3. product-service
cd product-service
mvn spring-boot:run
```

The service starts on `http://localhost:8083` and should appear in the
Eureka dashboard shortly after.
