package com.cognizant.paymentservice.service;

import com.cognizant.paymentservice.client.ThirdPartyPaymentClient;
import com.cognizant.paymentservice.dto.PaymentRequest;
import com.cognizant.paymentservice.dto.PaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final ThirdPartyPaymentClient thirdPartyPaymentClient;

    @Override
    @CircuitBreaker(name = "paymentGateway", fallbackMethod = "fallbackPayment")
    public PaymentResponse processPayment(PaymentRequest request) {
        String txnId = thirdPartyPaymentClient.charge(request.getOrderId(), request.getAmount().doubleValue());
        return new PaymentResponse(request.getOrderId(), "SUCCESS", "Payment processed successfully", txnId);
    }

    public PaymentResponse fallbackPayment(PaymentRequest request, Throwable throwable) {
        log.error("Fallback triggered for orderId={} due to: {}", request.getOrderId(), throwable.toString());
        return new PaymentResponse(
                request.getOrderId(),
                "FALLBACK",
                "Payment gateway is currently unavailable. Your payment has been queued and will be retried.",
                null
        );
    }
}
