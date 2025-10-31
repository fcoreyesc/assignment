package com.reyes.assignment.domain.card;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Getter
public class CreditCard extends Card {

    private static final BigDecimal FEE_PERCENTAGE = new BigDecimal("1.01");


    @Override
    public CardType getType() {
        return CardType.CREDIT;
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return FEE_PERCENTAGE.multiply(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
