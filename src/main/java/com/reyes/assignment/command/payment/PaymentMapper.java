package com.reyes.assignment.command.payment;

import com.reyes.assignment.domain.payment.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMapper {

    public Payment toDomain(PaymentCommand.PaymentRequest request, UUID accountId) {
        return Payment.builder()
                .accountId(accountId)
                .cardId(request.cardId())
                .amount(request.amount())
                .description(request.description())
                .build();
    }
}
