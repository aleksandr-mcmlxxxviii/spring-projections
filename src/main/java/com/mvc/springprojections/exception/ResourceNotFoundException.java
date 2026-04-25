package com.mvc.springprojections.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(String.format(message));
    }
}
