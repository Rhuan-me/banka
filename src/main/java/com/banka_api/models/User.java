package com.banka_api.models;

import com.banka_api.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") // Define o nome da tabela no banco de dados
@Data // Anotação do Lombok para gerar getters, setters, toString(), etc.
@NoArgsConstructor // Lombok para gerar um construtor sem argumentos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email; // Usado para login e comunicação

    @Column(nullable = false)
    private String countryOfOrigin; // País do documento (ex: "Guiné")

    private String documentIdNumber; // Número do documento de identidade

    @Enumerated(EnumType.STRING) // Armazena o status como texto (mais legível)
    @Column(nullable = false)
    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Relacionamento: Cada usuário tem uma conta bancária.
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;

    @PrePersist // Executa antes de salvar pela primeira vez
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = UserStatus.PENDING_VERIFICATION; // Status inicial padrão
    }

    @PreUpdate // Executa antes de atualizar
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
