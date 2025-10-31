package com.reyes.assignment.repository.card;


import com.reyes.assignment.domain.card.Card;
import com.reyes.assignment.domain.card.CardStatus;
import com.reyes.assignment.domain.card.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "card_type")
public abstract class CardEntity {

    @Id
    protected UUID id;
    protected UUID accountId;
    protected String cardNumber;
    protected String cvv;
    protected String expiryDate;
    protected CardStatus status;

    public abstract CardType getType();
    protected abstract Card createDomainCard();
}
