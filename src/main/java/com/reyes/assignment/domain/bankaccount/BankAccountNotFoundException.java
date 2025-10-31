package com.reyes.assignment.domain.bankaccount;

import com.reyes.assignment.domain.exception.NotFoundException;
import com.reyes.assignment.domain.exception.Parameter;

public class BankAccountNotFoundException extends NotFoundException {
    public BankAccountNotFoundException(String message, Parameter... parameters) {
        super(message, parameters);
    }
}
