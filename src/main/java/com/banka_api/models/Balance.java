package com.banka_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal; // Ideal para valores monetários, evita erros de arredondamento

@Entity
@Table(name = "balances")
@Data
@NoArgsConstructor
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private String currencyCode; // Ex: "EUR", "USDC"

    @Column(nullable = false, precision = 19, scale = 8) // Alta precisão para cripto e moedas
    private BigDecimal amount;

    public Balance(Account account, String currencyCode, BigDecimal amount) {
        this.account = account;
        this.currencyCode = currencyCode;
        this.amount = amount;
    }
}
