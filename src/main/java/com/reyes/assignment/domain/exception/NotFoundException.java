package com.reyes.assignment.domain.exception;

/**
 *
 */
public class NotFoundException extends DomainException {
    public NotFoundException(String message, Parameter... parameters) {
        super(message, parameters);
    }
}
