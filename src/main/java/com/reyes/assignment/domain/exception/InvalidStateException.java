package com.reyes.assignment.domain.exception;

/**
 * Handle the exception when the state is invalid
 */
public class InvalidStateException extends DomainException {
    public InvalidStateException(String message, Parameter... parameters) {
        super(message, parameters);
    }
}
