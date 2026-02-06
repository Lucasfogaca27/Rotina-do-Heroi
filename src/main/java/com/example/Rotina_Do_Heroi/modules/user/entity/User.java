package com.example.Rotina_Do_Heroi.modules.user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data // Essa anotação do Lombok gera os Getters e Setters automaticamente
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // HEROO27

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Gamification fields
    private Integer level = 1;
    private Long currentXp = 0L;
    private Long nextLevelXp = 100L;

    // Hero Attributes
    private Integer strength = 0;
    private Integer intelligence = 0;
    private Integer focus = 0;
}