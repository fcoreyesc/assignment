package com.reyes.assignment.repository.card;

import com.reyes.assignment.domain.card.Card;
import com.reyes.assignment.domain.card.CardType;
import com.reyes.assignment.domain.card.DebitCard;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEBIT")
public class DebitCardEntity extends CardEntity {
    @Override
    public CardType getType() {
        return CardType.DEBIT;
    }

    @Override
    protected Card createDomainCard() {
        return DebitCard.builder()
                .id(id)
                .accountId(accountId)
                .cardNumber(cardNumber)
                .cvv(cvv)
                .expiryDate(expiryDate)
                .status(status)
                .build();
    }
}
