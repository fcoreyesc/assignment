package com.reyes.assignment.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardJpaRepository extends JpaRepository<CardEntity, UUID> {

}