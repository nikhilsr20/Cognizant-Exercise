# Config Server

Part of **Exercise 2 – Inventory Management System with Service Discovery**.

Centralized configuration server. Product Service and Inventory
Service pull their configuration (ports, datasource URLs, feature
flags) from here at startup instead of bundling it locally.

## Tech Stack
- Spring Boot 3.2.5
- Spring Cloud Config Server (Spring Cloud 2023.0.1)

## Port
`8888`

## How it works
This server runs with the `native` profile, which serves configuration
files bundled on the classpath under `config-repo/` instead of pulling
from a remote Git repository (simpler for local/exercise purposes):

```yaml
spring:
  profiles:
    active: native
  cloud:
    config:
      server:
        native:
          search-locations: classpath:/config-repo
```

`config-repo/` contains:
- `product-service.yml` – port, MySQL datasource, `inventory.low-stock-threshold`
- `inventory-service.yml` – port, MySQL datasource, `product-service.name`

> To point this at a real Git repo instead, swap the `native` block for:
> ```yaml
> spring.cloud.config.server.git.uri: https://github.com/your-org/config-repo
> ```

## Endpoints
Spring Cloud Config exposes configuration over HTTP, e.g.:
```
GET http://localhost:8888/product-service/default
GET http://localhost:8888/inventory-service/default
```

## Running locally
```bash
cd config-server
mvn spring-boot:run
```

> Start this **after** Eureka Server, and **before** Product Service /
> Inventory Service (both import config from this server on startup).
