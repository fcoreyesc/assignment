package com.reyes.assignment.command.payment;

import com.reyes.assignment.domain.payment.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class PaymentCommand {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentCommand(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @PostMapping("/{accountId}/payments/")
    public ResponseEntity<?> pay(@PathVariable String accountId, @Valid @RequestBody PaymentRequest request) {
        paymentService.pay(paymentMapper.toDomain(request, UUID.fromString(accountId)));
        return ResponseEntity.accepted().build();
    }


    public record PaymentRequest(
            @NotNull UUID cardId,
            @NotNull BigDecimal amount,
            String description
    ) {
    }

}
