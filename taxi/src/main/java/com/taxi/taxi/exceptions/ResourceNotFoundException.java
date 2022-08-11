package com.taxi.taxi.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(final String message) {
        super(message);
    }
    
}
