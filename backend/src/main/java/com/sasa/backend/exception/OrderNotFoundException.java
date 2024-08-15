package com.sasa.backend.exception;

public class OrderNotFoundException extends RuntimeException {
    
    // Constructor that accepts a custom message
    public OrderNotFoundException(String message) {
        super(message);
    }
}
