package com.reyes.assignment.domain.payment;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class Payment {

    private UUID accountId;
    private UUID cardId;

    private BigDecimal amount;
    private String description;

}
