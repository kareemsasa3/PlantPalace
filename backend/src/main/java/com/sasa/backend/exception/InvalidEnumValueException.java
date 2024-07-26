package com.sasa.backend.exception;

public class InvalidEnumValueException extends RuntimeException {
    
    public InvalidEnumValueException(String message) {
        super(message);
    }
}
