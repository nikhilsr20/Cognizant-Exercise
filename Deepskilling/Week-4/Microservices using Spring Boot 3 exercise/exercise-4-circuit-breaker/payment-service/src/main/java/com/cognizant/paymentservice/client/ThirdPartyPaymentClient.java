package com.cognizant.paymentservice.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;


@Component
@Slf4j
public class ThirdPartyPaymentClient {

    private final WebClient webClient;

    public ThirdPartyPaymentClient(@Value("${third-party.payment-gateway.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public String charge(Long orderId, double amount) {
        log.info("Calling third-party payment gateway for orderId={}", orderId);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/simulate/charge")
                        .queryParam("orderId", orderId)
                        .queryParam("amount", amount)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(3))
                .block();
    }
}
