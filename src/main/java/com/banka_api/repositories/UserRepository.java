package com.banka_api.repositories;

import com.banka_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // <Model, Tipo do ID>

    // O Spring Data JPA cria a implementação deste método automaticamente
    // baseado no nome: "busque um User pelo campo email"
    Optional<User> findByEmail(String email);
}