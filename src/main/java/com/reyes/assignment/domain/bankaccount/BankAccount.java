package com.reyes.assignment.domain.bankaccount;

import com.reyes.assignment.domain.card.Card;
import com.reyes.assignment.domain.card.CardType;
import com.reyes.assignment.domain.event.DomainEvent;
import com.reyes.assignment.domain.exception.InvalidInputException;
import com.reyes.assignment.domain.exception.Parameter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class BankAccount {

    private UUID id;
    private UUID userId;

    private String iban;
    private BigDecimal balance = BigDecimal.ZERO;
    private Map<CardType, Card> cards;

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidInputException("amount-cannot-be-negative", Parameter.of("amount", amount.toString()));
        }

    }

    private void validateBalance(BigDecimal amount) {
        var newBalance = balance.add(amount.negate());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidInputException("balance-cannot-be-negative", Parameter.of("amount", amount.toString()),Parameter.of("newBalance", newBalance.toString()));
        }

    }

    public PaymentMadeEvent pay(Card card,String description, BigDecimal amount) {
        validateAmount(amount);

        var fee = card.calculateFee(amount);
        var totalAmount = amount.add(fee).setScale(2, BigDecimal.ROUND_HALF_UP);

        validateBalance(totalAmount);
        balance = balance.subtract(totalAmount);

        return  PaymentMadeEvent.builder()
                .accountId(id)
                .balance(balance)
                .description(description)
                .cardType(card.getType())
                .fee(fee)
                .amount(amount)
                .build();
    }

    @Builder
    @Getter
    public static class PaymentMadeEvent implements DomainEvent {
        private UUID accountId;
        private CardType cardType;
        private BigDecimal amount;
        private BigDecimal fee;
        private BigDecimal balance;
        private String description;

    }

//    public Transaction transfer(BankAccount destination, BigDecimal amount) {
//        validateBalance(amount);
//        balance = balance.subtract(amount);
//        return Transaction.builder()
//                .accountId(id)
//                .balance(balance)
//                .description(description)
//                .transactionType(card.getType()==CardType.DEBIT?Transaction.TransactionType.PAYMENT_DEBIT:Transaction.TransactionType.PAYMENT_CREDIT)
//                .fee(card.calculateFee(amount))
//                .createdAt(LocalDateTime.now())
//                .amount(amount)
//                .build();
//    }
//
//    public Transaction transferExternal(String destinationIBAN, BigDecimal amount) {
//        validateBalance(amount);
//    }
//
//
    public Transaction withdraw(BigDecimal amount) {
        validateAmount(amount);

        var fee = card.calculateFee(amount);
        var totalAmount = amount.add(fee).setScale(2, BigDecimal.ROUND_HALF_UP);

        validateBalance(totalAmount);
        balance = balance.subtract(totalAmount);

        return  PaymentMadeEvent.builder()
                .accountId(id)
                .balance(balance)
                .description(description)
                .cardType(card.getType())
                .fee(fee)
                .amount(amount)
                .build();
    }
//
//    public Transaction deposit(BigDecimal amount) {
//
//    }

    @Builder
    @Getter
    public static class PaymentEvent{
        private UUID accountId;
        private BigDecimal fee;
        private BigDecimal amount;
        private BigDecimal balance;
        private String description;
        private CardType cardType;

    }


}
