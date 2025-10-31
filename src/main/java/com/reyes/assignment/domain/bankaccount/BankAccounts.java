package com.reyes.assignment.domain.bankaccount;

import java.util.UUID;

public interface BankAccounts {

    BankAccount findById(UUID id);

    BankAccount findByIdWithLock(UUID id);

    BankAccount save(BankAccount bankAccount);

    void delete(UUID id);

    BankAccount update(BankAccount bankAccount);


}
