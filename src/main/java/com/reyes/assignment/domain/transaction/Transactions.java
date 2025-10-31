package com.reyes.assignment.domain.transaction;

import com.reyes.assignment.repository.transaction.TransactionEntity;

import java.util.UUID;

public interface Transactions {

    Transaction findById(UUID id);
    Transaction save(Transaction transaction);
    Transaction update(Transaction transaction);
    void delete(UUID id);
}
