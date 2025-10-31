package com.reyes.assignment.command.withdrawals;

import com.reyes.assignment.command.payment.PaymentCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class WithdrawalCommand {


    @PostMapping("/{accountId}/withdrawal/")
    public ResponseEntity<?> withdrawal(@PathVariable String accountId, @Valid @RequestBody WithdrawRequest request) {
        paymentService.pay(paymentMapper.toDomain(request, UUID.fromString(accountId)));
        return ResponseEntity.accepted().build();
    }

    public record WithdrawRequest(
            @NotNull UUID cardId,
            @NotNull BigDecimal amount
    ) {
    }

}
