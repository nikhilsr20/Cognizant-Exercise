package com.cognizant.paymentservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@RestController
@Slf4j
public class ThirdPartyPaymentSimulatorController {

    @GetMapping("/simulate/charge")
    public String charge(@RequestParam Long orderId, @RequestParam double amount) throws InterruptedException {
        double roll = ThreadLocalRandom.current().nextDouble();

        if (roll < 0.2) {
            log.warn("Simulating third-party gateway error for orderId={}", orderId);
            throw new RuntimeException("Third-party payment gateway error");
        }

        if (roll < 0.6) {
            log.warn("Simulating slow third-party gateway response for orderId={}", orderId);
            Thread.sleep(4000);
        }

        return "txn-" + UUID.randomUUID();
    }
}
