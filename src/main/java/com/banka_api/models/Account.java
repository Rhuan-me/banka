package com.banka_api.models;

import com.banka_api.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento: A conta pertence a um único usuário.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Chave estrangeira para a tabela users
    private User user;

    @Column(unique = true)
    private String iban; // IBAN Europeu

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    // Relacionamento: Uma conta pode ter vários saldos (um para cada moeda)
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Balance> balances;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = AccountStatus.PENDING_ACTIVATION; // A conta é criada mas só ativa após validação
    }
}

