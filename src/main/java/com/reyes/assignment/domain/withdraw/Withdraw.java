package com.reyes.assignment.domain.withdraw;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class Withdraw {
    private UUID accountId;
    private BigDecimal amount;
}
