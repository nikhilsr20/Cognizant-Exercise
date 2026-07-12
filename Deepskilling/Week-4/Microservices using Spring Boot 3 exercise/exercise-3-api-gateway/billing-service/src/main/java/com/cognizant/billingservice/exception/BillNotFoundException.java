package com.cognizant.billingservice.exception;

public class BillNotFoundException extends RuntimeException {
    public BillNotFoundException(Long id) {
        super("Bill not found with id: " + id);
    }
}
