package com.reyes.assignment.command;

import com.reyes.assignment.domain.bankaccount.BankAccounts;
import com.reyes.assignment.domain.payment.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentCommand {

    private BankAccounts bankAccounts;
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<?> payDebit(@Valid @RequestBody PaymentRequest req) {
        var account = bankAccounts.findById(req.accountId());

        paymentService.
        accountService.payWithDebit(acc, req.amount(), card, req.description());
        return ResponseEntity.accepted().build();
    }

    public record PaymentRequest(
            @NotNull UUID accountId,
            @NotNull UUID cardId,
            @NotNull BigDecimal amount,
            String description
    ) {}

}
