package com.reyes.assignment.repository.bankaccount;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BankAccountJpaRepository extends JpaRepository<BankAccountEntity, UUID> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM BankAccountEntity b WHERE b.id = :id")
    Optional<BankAccountEntity> findByIdWithLock(UUID id);

}
