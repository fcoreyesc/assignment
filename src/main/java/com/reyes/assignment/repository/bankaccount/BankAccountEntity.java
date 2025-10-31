package com.reyes.assignment.repository.bankaccount;

import com.reyes.assignment.repository.card.CardEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private UserEntity user;

    @Column(name = "iban", nullable = false, unique = true, length = 34)
    private String iban;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CardEntity> cards = new ArrayList<>();
}