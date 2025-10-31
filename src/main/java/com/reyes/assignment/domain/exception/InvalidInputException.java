package com.reyes.assignment.domain.exception;

/**
 * Handle the exception when the input is invalid
 */
public class InvalidInputException extends DomainException {
    public InvalidInputException(String message, Parameter... parameters) {
        super(message, parameters);
    }
}
