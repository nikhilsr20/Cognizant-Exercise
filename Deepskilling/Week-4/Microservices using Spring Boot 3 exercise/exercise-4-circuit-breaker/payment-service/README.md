# Payment Service

Part of **Exercise 4 – Resilient Microservices with Circuit Breaker**.

Calls a slow/unreliable third-party payment gateway behind
a Resilience4j Circuit Breaker, with fallback logic and event logging.

## Tech Stack
- Spring Boot 3.2.5
- Spring Web + Spring WebFlux (`WebClient` for the outbound call)
- Resilience4j (`resilience4j-spring-boot3`)
- Spring Boot Actuator (exposes circuit breaker health/metrics)
- Lombok

## Package Structure
```
com.cognizant.paymentservice
├── controller
│   ├── PaymentController                     # POST /api/payments/pay
│   └── ThirdPartyPaymentSimulatorController   # stands in for the real gateway
├── service            # PaymentService + Resilience4j-annotated impl
├── client             # ThirdPartyPaymentClient (WebClient)
├── dto
└── config             # CircuitBreakerEventLogger
```

## Port
`8087`

## Why a simulator?
There's no real third-party payment gateway to call in this exercise,
so `ThirdPartyPaymentSimulatorController` plays that role on the same
service: ~40% of calls sleep 4 seconds (breaching the 3s client-side
timeout), ~20% throw an error outright, and the rest succeed
immediately. This gives the circuit breaker realistic failure/slow-call
patterns to react to without needing an external dependency.

## Circuit breaker configuration
```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentGateway:
        sliding-window-size: 10
        minimum-number-of-calls: 5
        failure-rate-threshold: 50
        slow-call-rate-threshold: 50
        slow-call-duration-threshold: 2s
        wait-duration-in-open-state: 10s
        permitted-number-of-calls-in-half-open-state: 3
```
- If ≥50% of the last 10 calls fail, or are slower than 2s, the circuit
  opens.
- While OPEN, calls short-circuit straight to `fallbackPayment(...)`
  without even attempting the real call.
- After 10 seconds it moves to HALF_OPEN and tries 3 calls to decide
  whether to close again.

## Fallback logic
```java
@CircuitBreaker(name = "paymentGateway", fallbackMethod = "fallbackPayment")
public PaymentResponse processPayment(PaymentRequest request) { ... }

public PaymentResponse fallbackPayment(PaymentRequest request, Throwable throwable) {
    // returns a "FALLBACK" status instead of propagating the failure
}
```

## Monitoring fallback / state-transition events
`CircuitBreakerEventLogger` subscribes to the breaker's event
publisher and logs every state transition, error, success, and
rejected call. You'll see log lines like:
```
WARN  Circuit Breaker 'paymentGateway' state transition: CLOSED -> OPEN
WARN  Circuit Breaker 'paymentGateway' rejected a call - circuit is OPEN
```

Circuit breaker health/metrics are also exposed via Actuator:
```
GET http://localhost:8087/actuator/health
GET http://localhost:8087/actuator/circuitbreakers
GET http://localhost:8087/actuator/circuitbreakerevents
```

## Endpoints

| Method | Path                | Description                                  |
|--------|---------------------|-----------------------------------------------|
| POST   | `/api/payments/pay` | Process a payment (circuit-breaker protected)   |

### Sample request
```http
POST /api/payments/pay
Content-Type: application/json

{
  "orderId": 1,
  "amount": 999.00
}
```

### Sample responses
Success:
```json
{ "orderId": 1, "status": "SUCCESS", "message": "Payment processed successfully", "transactionId": "txn-..." }
```
Fallback (circuit open, or the call failed/timed out):
```json
{ "orderId": 1, "status": "FALLBACK", "message": "Payment gateway is currently unavailable. Your payment has been queued and will be retried.", "transactionId": null }
```

## Running locally
```bash
cd payment-service
mvn spring-boot:run
```

Call `POST /api/payments/pay` repeatedly (e.g. in a loop) to observe
some calls succeed, some hit the 3s timeout and fall back, and — once
enough slow/failed calls accumulate — the breaker opening and every
subsequent call short-circuiting straight to the fallback until it
tests the waters again in HALF_OPEN.
