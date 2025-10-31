package com.reyes.assignment.repository.card;

import com.reyes.assignment.domain.card.Card;
import com.reyes.assignment.domain.card.Cards;
import com.reyes.assignment.domain.exception.NotFoundException;
import com.reyes.assignment.domain.exception.Parameter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CardJpaAdapter implements Cards {

    private final CardJpaRepository cardJpaRepository;
    private final CardMapper cardMapper;

    public CardJpaAdapter(CardJpaRepository cardJpaRepository, CardMapper cardMapper) {
        this.cardJpaRepository = cardJpaRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public Card findById(UUID id) {
        return cardJpaRepository.findById(id)
                .map(cardMapper::toDomain)
                .orElseThrow(() -> notFoundException(id));
    }

    @Override
    public Card save(Card card) {
        CardEntity entity = cardMapper.toEntity(card);
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        CardEntity savedEntity = cardJpaRepository.save(entity);
        return cardMapper.toDomain(savedEntity);
    }

    @Override
    public Card update(Card card) {
        if (!cardJpaRepository.existsById(card.getId())) {
            throw notFoundException(card.getId());
        }
        CardEntity entity = cardMapper.toEntity(card);
        CardEntity updatedEntity = cardJpaRepository.save(entity);
        return cardMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        if (!cardJpaRepository.existsById(id)) {
            throw notFoundException(id);
        }
        cardJpaRepository.deleteById(id);
    }

    private NotFoundException notFoundException(UUID id) {
        return new NotFoundException("card-not-found", Parameter.of("id", id.toString()));
    }
}
