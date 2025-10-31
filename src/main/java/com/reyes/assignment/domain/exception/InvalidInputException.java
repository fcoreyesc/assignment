package com.reyes.assignment.domain.exception;

/**
 * Handle the exception when the user input is invalid
 */
public class UserInputException extends DomainException {
    public UserInputException(String message) {
        super(message);
    }
}
