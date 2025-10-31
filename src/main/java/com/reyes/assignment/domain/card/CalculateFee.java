package com.reyes.assignment.domain.card;

import java.math.BigDecimal;

public interface CalculateFee {
    default BigDecimal calculateFee(BigDecimal amount){
        return BigDecimal.ZERO;
    }
}
