package com.reyes.assignment.domain.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
