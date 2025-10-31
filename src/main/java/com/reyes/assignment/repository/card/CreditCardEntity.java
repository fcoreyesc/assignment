package com.reyes.assignment.repository.card;

import com.reyes.assignment.domain.card.CardType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEBIT")
public class DebitCardEntity extends CardEntity {
    @Override
    public CardType getType() {
        return CardType.DEBIT;
    }
}
