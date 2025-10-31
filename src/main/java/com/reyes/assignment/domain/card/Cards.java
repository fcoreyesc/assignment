package com.reyes.assignment.domain.card;

import java.util.UUID;

public interface Cards {
    Card findById(UUID id);

    Card save(Card card);

    Card update(Card card);

    void delete(UUID id);
}
