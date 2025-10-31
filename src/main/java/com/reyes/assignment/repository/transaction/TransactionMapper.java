package com.reyes.assignment.repository.transaction;

import com.reyes.assignment.domain.transaction.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    /**
     * Convert TransactionEntity to Transaction domain object
     */
    public Transaction toDomain(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }

        return Transaction.builder()
                .id(entity.getId())
                .accountId(entity.getAccountId())
                .transactionType(entity.getTransactionType())
                .amount(entity.getAmount())
                .balance(entity.getBalance())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .relatedAccountId(entity.getRelatedAccountId())
                .externalAccountIban(entity.getExternalAccountIban())
                .externalAccountName(entity.getExternalAccountName())
                .externalAccountEmail(entity.getExternalAccountEmail())
                .build();
    }

    /**
     * Convert Transaction domain object to TransactionEntity
     */
    public TransactionEntity toEntity(Transaction domain) {
        if (domain == null) {
            return null;
        }

        return TransactionEntity.builder()
                .id(domain.getId())
                .accountId(domain.getAccountId())
                .transactionType(domain.getTransactionType())
                .amount(domain.getAmount())
                .balance(domain.getBalance())
                .description(domain.getDescription())
                .createdAt(domain.getCreatedAt())
                .relatedAccountId(domain.getRelatedAccountId())
                .externalAccountIban(domain.getExternalAccountIban())
                .externalAccountName(domain.getExternalAccountName())
                .externalAccountEmail(domain.getExternalAccountEmail())
                .build();
    }
}
