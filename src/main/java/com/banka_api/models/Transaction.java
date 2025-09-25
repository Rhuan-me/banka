package com.banka_api.models;

import com.banka_api.enums.TransactionStatus;
import com.banka_api.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id") // Pode ser nulo para depósitos externos (ajuda da ONG)
    private Account sourceAccount;

    // Para remessas, o destino pode não ser uma conta Banka
    private String destinationIdentifier; // Ex: número de telefone do Orange Money

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currencyCode;

    private BigDecimal fee; // Taxa da transação

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = TransactionStatus.PENDING;
    }
}



