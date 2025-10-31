package com.reyes.assignment.domain.payment;

import com.reyes.assignment.domain.bankaccount.BankAccount;
import com.reyes.assignment.domain.bankaccount.BankAccounts;
import com.reyes.assignment.domain.card.Card;
import com.reyes.assignment.domain.card.Cards;
import com.reyes.assignment.domain.event.DomainEventPublisher;
import com.reyes.assignment.domain.exception.InvalidInputException;
import com.reyes.assignment.domain.exception.Parameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final DomainEventPublisher domainEventPublisher;
    private final BankAccounts bankAccounts;
    private final Cards cards;

    public PaymentService(DomainEventPublisher domainEventPublisher, BankAccounts bankAccounts, Cards cards) {
        this.domainEventPublisher = domainEventPublisher;
        this.bankAccounts = bankAccounts;
        this.cards = cards;
    }


    @Transactional
    public void pay(Payment payment) {
        var bankAccount = bankAccounts.findByIdWithLock(payment.getAccountId());
        var card = cards.findById(payment.getCardId());

        validateCardBelongsToAccount(payment, card, bankAccount);

        var event = bankAccount.pay(card, payment.getDescription(), payment.getAmount());
        domainEventPublisher.publish(event);
        bankAccounts.save(bankAccount);
    }

    private static void validateCardBelongsToAccount(Payment payment, Card card, BankAccount bankAccount) {
        if (!card.getAccountId().equals(bankAccount.getId())) {
            throw new InvalidInputException(
                    "card-does-not-belong-to-the-account",
                    Parameter.of("cardId", payment.getCardId().toString()),
                    Parameter.of("accountId", payment.getAccountId().toString()),
                    Parameter.of("cardAccountId", card.getAccountId().toString())
            );
        }
    }

}
