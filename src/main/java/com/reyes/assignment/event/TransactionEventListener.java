package com.reyes.assignment.event;

import com.reyes.assignment.domain.bankaccount.BankAccount;
import com.reyes.assignment.domain.card.CardType;
import com.reyes.assignment.domain.transaction.Transaction;
import com.reyes.assignment.domain.transaction.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionEventListener {

    private final Transactions transactions;

    @Autowired
    public TransactionEventListener(Transactions transactions) {
        this.transactions = transactions;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void on(BankAccount.PaymentMadeEvent event) {

        var newTransaction = Transaction.builder()
                .id(UUID.randomUUID())
                .accountId(event.getAccountId())
                .balance(event.getBalance())
                .createdAt(LocalDateTime.now())
                .amount(event.getAmount())
                .description(event.getDescription())
                .transactionType(event.getCardType() == CardType.DEBIT ? Transaction.TransactionType.PAYMENT_DEBIT : Transaction.TransactionType.PAYMENT_CREDIT)
                .build();

        transactions.save(newTransaction);
    }

}
