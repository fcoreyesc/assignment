package com.reyes.assignment.repository.transaction;

import com.reyes.assignment.domain.exception.NotFoundException;
import com.reyes.assignment.domain.exception.Parameter;
import com.reyes.assignment.domain.transaction.Transaction;
import com.reyes.assignment.domain.transaction.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionJpaAdapter implements Transactions {

    private final TransactionJpaRepository transactionJpaRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionJpaAdapter(TransactionJpaRepository transactionJpaRepository, TransactionMapper transactionMapper) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Transaction findById(UUID id) {
        return transactionJpaRepository.findById(id)
                .map(transactionMapper::toDomain)
                .orElseThrow(() -> notFoundException(id));
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = transactionMapper.toEntity(transaction);
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        TransactionEntity savedEntity = transactionJpaRepository.save(entity);
        return transactionMapper.toDomain(savedEntity);
    }

    @Override
    public Transaction update(Transaction transaction) {
        if (!transactionJpaRepository.existsById(transaction.getId())) {
            throw notFoundException(transaction.getId());
        }
        TransactionEntity entity = transactionMapper.toEntity(transaction);
        TransactionEntity updatedEntity = transactionJpaRepository.save(entity);
        return transactionMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        if (!transactionJpaRepository.existsById(id)) {
            throw notFoundException(id);
        }
        transactionJpaRepository.deleteById(id);
    }

    private NotFoundException notFoundException(UUID id) {
        return new NotFoundException("transaction-not-found", Parameter.of("id", id.toString()));
    }
}
