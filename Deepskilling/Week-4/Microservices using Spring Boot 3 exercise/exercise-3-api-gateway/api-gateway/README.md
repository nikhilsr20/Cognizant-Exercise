# API Gateway

Part of **Exercise 3 – API Gateway**.

Single entry point that routes external traffic to Customer Service
and Billing Service, built with Spring Cloud Gateway.

## Tech Stack
- Spring Boot 3.2.5
- Spring Cloud Gateway (reactive, Spring Cloud 2023.0.1)
- Spring Data Redis Reactive (backs the rate limiter)
- Spring Boot Actuator

## Port
`8080`

## What it demonstrates

### 1. Routing
```yaml
routes:
  - id: customer-service-route
    uri: http://localhost:8085
    predicates:
      - Path=/customers/**
  - id: billing-service-route
    uri: http://localhost:8086
    predicates:
      - Path=/billing/**
```

### 2. Path rewriting
Public-facing paths are shorter/cleaner than the internal service
paths:
```
/customers/**  ->  /api/customers/**   (Customer Service)
/billing/**    ->  /api/bills/**       (Billing Service)
```
via the `RewritePath` gateway filter.

### 3. Rate limiting
A Redis-backed token-bucket `RequestRateLimiter` is applied per client
IP (`ipKeyResolver`), allowing 5 requests/sec with a burst of 10:
```yaml
redis-rate-limiter.replenishRate: 5
redis-rate-limiter.burstCapacity: 10
key-resolver: "#{@ipKeyResolver}"
```
Requires a local Redis instance:
```bash
docker run -p 6379:6379 redis
```
Exceeding the limit returns `HTTP 429 Too Many Requests`.

### 4. Caching
Spring Cloud Gateway's built-in `LocalResponseCache` filter caches GET
responses for 30 seconds (per route), reducing load on downstream
services for repeated reads:
```yaml
spring.cloud.gateway.filter.local-response-cache.enabled: true
```

### 5. Custom global filter
`LoggingGlobalFilter` logs every request/response that passes through
the gateway, independent of which route matched.

## Running locally
```bash
# 1. Start Redis
docker run -p 6379:6379 redis

# 2. Start the downstream services
cd customer-service && mvn spring-boot:run &
cd billing-service  && mvn spring-boot:run &

# 3. Start the gateway
cd api-gateway
mvn spring-boot:run
```

## Try it
```bash
curl http://localhost:8080/customers
curl http://localhost:8080/billing
```
Fire more than 10 requests in quick succession against either route to
see the rate limiter kick in with `429` responses.
