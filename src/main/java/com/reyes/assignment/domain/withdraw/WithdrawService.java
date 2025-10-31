package com.reyes.assignment.domain.withdraw;

import com.reyes.assignment.domain.bankaccount.BankAccounts;
import com.reyes.assignment.domain.card.Cards;
import com.reyes.assignment.domain.event.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawService {

    private final DomainEventPublisher domainEventPublisher;
    private final BankAccounts bankAccounts;

    @Autowired
    public WithdrawService(DomainEventPublisher domainEventPublisher, BankAccounts bankAccounts) {
        this.domainEventPublisher = domainEventPublisher;
        this.bankAccounts = bankAccounts;
    }

    public void withdraw(Withdraw withdraw){
        var bankAccount = bankAccounts.findByIdWithLock(withdraw.getAccountId());

        bankAccount.;
    }


}
