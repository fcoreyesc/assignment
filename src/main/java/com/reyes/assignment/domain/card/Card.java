package com.reyes.assignment.domain.card;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
public abstract class Card implements CalculateFee {

    protected UUID id;
    protected UUID accountId;
    protected String cardNumber;
    protected String cvv;
    protected String expiryDate;
    protected CardStatus status;

    abstract public CardType getType();

}
