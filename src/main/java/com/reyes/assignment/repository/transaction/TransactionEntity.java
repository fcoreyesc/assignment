package com.reyes.assignment.repository.transaction;

import com.reyes.assignment.domain.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_id", insertable = false, updatable = false)
//    private BankAccountEntity bankAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false, length = 20)
    private Transaction.TransactionType transactionType;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    // For internal transactions
    @Column(name = "related_account_id")
    private UUID relatedAccountId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "related_account_id", insertable = false, updatable = false)
//    private BankAccountEntity relatedAccount;

    // For external transactions
    @Column(name = "external_account_iban", length = 34)
    private String externalAccountIban;

    @Column(name = "external_account_name", length = 255)
    private String externalAccountName;

    @Column(name = "external_account_email", length = 255)
    private String externalAccountEmail;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
