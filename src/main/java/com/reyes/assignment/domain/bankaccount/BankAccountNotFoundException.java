package com.reyes.assignment.domain.bankaccount;

public class BankAccountNotFoundExceptio extends RuntimeException {
    public BankAccountNotFoundExceptio(String message) {
        super(message);
    }
}
