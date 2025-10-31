package com.reyes.assignment.repository.bankaccount;

import com.reyes.assignment.domain.bankaccount.BankAccount;
import com.reyes.assignment.domain.card.Card;
import com.reyes.assignment.domain.card.CardType;
import com.reyes.assignment.repository.card.CardMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BankAccountMapper {

    private final CardMapper cardMapper;

    public BankAccountMapper(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    /**
     * Convert BankAccountEntity to BankAccount domain object
     */
    public BankAccount toDomain(BankAccountEntity entity) {
        if (entity == null) {
            return null;
        }

        Map<CardType, Card> cardsMap = entity.getCards() != null
                ? entity.getCards().stream()
                .map(cardMapper::toDomain)
                .collect(Collectors.toMap(Card::getType, card -> card))
                : null;

        return BankAccount.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .iban(entity.getIban())
                .balance(entity.getBalance())
                .cards(cardsMap)
                .build();
    }

    /**
     * Convert BankAccount domain object to BankAccountEntity
     */
    public BankAccountEntity toEntity(BankAccount domain) {
        if (domain == null) {
            return null;
        }

        return BankAccountEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .iban(domain.getIban())
                .balance(domain.getBalance())
                .cards(domain.getCards() != null ? domain.getCards().values().stream().map(cardMapper::toEntity).collect(Collectors.toList()) : null)
                .build();
    }
}
