package com.reyes.assignment.domain.transaction;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Transaction {
    private UUID id;
    private UUID accountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;
    private String description;
    private LocalDateTime createdAt;

    // for internal transactions
    private UUID relatedAccountId;

    // for external transactions
    private String externalAccountIban;
    private String externalAccountName;
    private String externalAccountEmail;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_OUT, TRANSFER_IN, PAYMENT_DEBIT, PAYMENT_CREDIT
    }

}
