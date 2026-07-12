# Eureka Server

Part of **Exercise 2 – Inventory Management System with Service Discovery**.

Netflix Eureka service registry. Product Service and Inventory Service
register themselves here on startup, and discover each other by
application name instead of hard-coded host/port pairs.

## Tech Stack
- Spring Boot 3.2.5
- Spring Cloud Netflix Eureka Server (Spring Cloud 2023.0.1)

## Port
`8761`

## Configuration
This instance does not register with itself and does not fetch a
registry (standalone mode), which is standard for a single-node setup:
```yaml
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

## Running locally
```bash
cd eureka-server
mvn spring-boot:run
```

Once running, open the dashboard at:
```
http://localhost:8761
```

Registered clients (Product Service, Inventory Service) will appear
under "Instances currently registered with Eureka" once they start up.

> Start Eureka Server **first**, before Config Server, Product Service,
> and Inventory Service.
