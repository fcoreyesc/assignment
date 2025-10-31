package com.reyes.assignment.repository.card;

import com.reyes.assignment.domain.card.Card;
import com.reyes.assignment.domain.card.CardType;
import com.reyes.assignment.domain.card.CreditCard;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CREDIT")
public class CreditCardEntity extends CardEntity {
    @Override
    public CardType getType() {
        return CardType.CREDIT;
    }

    @Override
    protected Card createDomainCard() {
        return CreditCard.builder().id(id)
                .accountId(accountId)
                .cardNumber(cardNumber)
                .cvv(cvv)
                .expiryDate(expiryDate)
                .status(status)
                .build();
    }
}
