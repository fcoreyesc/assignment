package com.reyes.assignment.domain.card;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class DebitCard extends Card{
    @Override
    public CardType getType() {
        return CardType.DEBIT;
    }
}
