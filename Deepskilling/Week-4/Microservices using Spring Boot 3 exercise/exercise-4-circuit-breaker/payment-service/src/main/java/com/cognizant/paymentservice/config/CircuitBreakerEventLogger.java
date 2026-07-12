package com.cognizant.paymentservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CircuitBreakerEventLogger {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    public void registerListeners() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("paymentGateway");

        circuitBreaker.getEventPublisher()
                .onStateTransition(event ->
                        log.warn("Circuit Breaker '{}' state transition: {} -> {}",
                                event.getCircuitBreakerName(),
                                event.getStateTransition().getFromState(),
                                event.getStateTransition().getToState()))
                .onError(event ->
                        log.error("Circuit Breaker '{}' recorded an error: {}",
                                event.getCircuitBreakerName(), event.getThrowable().toString()))
                .onSuccess(event ->
                        log.info("Circuit Breaker '{}' recorded a success (duration={}ms)",
                                event.getCircuitBreakerName(), event.getElapsedDuration().toMillis()))
                .onCallNotPermitted(event ->
                        log.warn("Circuit Breaker '{}' rejected a call - circuit is OPEN",
                                event.getCircuitBreakerName()));
    }
}
