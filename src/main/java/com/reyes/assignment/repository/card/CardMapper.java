package com.reyes.assignment.repository.card;

import com.reyes.assignment.domain.card.Card;
import com.reyes.assignment.domain.card.CardType;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    /**
     * Convert CardEntity to Card domain object
     * Handles both CreditCard and DebitCard types
     */
    public Card toDomain(CardEntity entity) {
        if (entity == null) {
            return null;
        }
        return  entity.createDomainCard();

    }

    /**
     * Convert Card domain object to CardEntity
     * Handles both CreditCard and DebitCard types
     */
    public CardEntity toEntity(Card domain) {
        if (domain == null) {
            return null;
        }

        CardEntity entity = createEntityByType(domain.getType());

        entity.setId(domain.getId());
        entity.setAccountId(domain.getAccountId());
        entity.setCardNumber(domain.getCardNumber());
        entity.setCvv(domain.getCvv());
        entity.setExpiryDate(domain.getExpiryDate());
        entity.setStatus(domain.getStatus());

        return entity;
    }

    private CardEntity createEntityByType(CardType type) {
        return switch (type) {
            case CREDIT -> new CreditCardEntity();
            case DEBIT -> new DebitCardEntity();
        };
    }
}