package com.reyes.assignment.repository.bankaccount;

import com.reyes.assignment.domain.bankaccount.BankAccount;
import com.reyes.assignment.domain.bankaccount.BankAccounts;
import com.reyes.assignment.domain.exception.NotFoundException;
import com.reyes.assignment.domain.exception.Parameter;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class BankAccountJpaAdapter implements BankAccounts {

    private final BankAccountJpaRepository repository;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountJpaAdapter(BankAccountJpaRepository repository, BankAccountMapper bankAccountMapper) {
        this.repository = repository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public BankAccount findById(UUID id) {
        return repository.findById(id).map(bankAccountMapper::toDomain)
                .orElseThrow(
                        () -> new NotFoundException("bank-account-not-found", Parameter.of("id", id.toString()))
                );
    }

    @Override
    public BankAccount findByIdWithLock(UUID id) {
        return repository.findByIdWithLock(id).map(bankAccountMapper::toDomain)
                .orElseThrow(
                        () -> new NotFoundException("bank-account-not-found", Parameter.of("id", id.toString()))
                );
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        if (bankAccount.getId() == null) {
            bankAccount = bankAccount.toBuilder().id(UUID.randomUUID()).build();
        }
        repository.save(bankAccountMapper.toEntity(bankAccount));
        return bankAccount;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        repository.save(bankAccountMapper.toEntity(bankAccount));
        return bankAccount;
    }


}
