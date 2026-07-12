package com.cognizant.paymentservice.service;

import com.cognizant.paymentservice.dto.PaymentRequest;
import com.cognizant.paymentservice.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
}
