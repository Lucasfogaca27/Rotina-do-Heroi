package com.example.Rotina_Do_Heroi.modules.user.repository;

import com.example.Rotina_Do_Heroi.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Método para buscar o seu herói pelo nickname (HEROO27)
    Optional<User> findByUsername(String username);

    // Método para verificar se um e-mail já está cadastrado
    Boolean existsByEmail(String email);
}