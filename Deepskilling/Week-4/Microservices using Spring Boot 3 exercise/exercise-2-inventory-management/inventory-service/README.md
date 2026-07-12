# Inventory Service

Part of **Exercise 2 – Inventory Management System with Service Discovery**.

Tracks stock levels for each product. Looks up product details from
**Product Service** through Eureka + OpenFeign — no hard-coded URLs.

## Tech Stack
- Spring Boot 3.2.5
- Spring Web (REST)
- Spring Data JPA + MySQL
- Spring Cloud Netflix Eureka Client
- Spring Cloud Config Client
- Spring Cloud OpenFeign + LoadBalancer
- Lombok

## Package Structure
```
com.cognizant.inventoryservice
├── controller
├── service
├── repository
├── entity
├── dto          # ProductDTO, InventoryResponse
├── client       # ProductServiceClient (OpenFeign, resolved via Eureka)
└── exception
```

## Port
`8084` (pulled from Config Server's `inventory-service.yml`)

## Service discovery in action
```java
@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @GetMapping("/api/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);
}
```
No URL is hard-coded — `"product-service"` is resolved to a live
instance via Eureka + Spring Cloud LoadBalancer.

## Endpoints

| Method | Path                                          | Description                                  |
|--------|-----------------------------------------------|-----------------------------------------------|
| POST   | `/api/inventory`                              | Create an inventory record for a product       |
| GET    | `/api/inventory`                              | List all inventory records                     |
| GET    | `/api/inventory/product/{productId}`          | Get stock + live product name (calls Product Service) |
| PATCH  | `/api/inventory/product/{productId}/adjust?delta=N` | Increase/decrease stock by `delta`        |

### Sample response — `GET /api/inventory/product/1`
```json
{
  "inventoryId": 1,
  "productId": 1,
  "productName": "Mechanical Keyboard",
  "quantityAvailable": 50,
  "warehouseLocation": "Bengaluru-WH1"
}
```
`productName` here is fetched live from Product Service at request time.

## Running locally
Start order matters:
```bash
# 1. eureka-server   (8761)
# 2. config-server    (8888)
# 3. product-service  (8083)
# 4. inventory-service
cd inventory-service
mvn spring-boot:run
```
