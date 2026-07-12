package com.cognizant.orderservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId + ". Cannot place order for a non-existing user.");
    }
}
