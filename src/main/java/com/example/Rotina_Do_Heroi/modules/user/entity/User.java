package com.example.Rotina_Do_Heroi.modules.user.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;

@Entity
@Table(name = "users")
//@Data // Essa anotação do Lombok gera os Getters e Setters automaticamente
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false, unique = true)
    private String username; // HEROO27

    //@Column(nullable = false, unique = true)
    private String email;

    //@Column(nullable = false)
    private String password;

    // Gamification fields
    private Integer level = 1;
    private Long currentXp = 0L;
    private Long nextLevelXp = 100L;

    // Hero Attributes
    private Integer strength = 0;
    private Integer intelligence = 0;
    private Integer focus = 0;


    // Dentro da classe User, remova as anotações do Lombok e adicione:
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public long getCurrentXp() { return currentXp; }
    public void setCurrentXp(long currentXp) { this.currentXp = currentXp; }

    public long getNextLevelXp() { return nextLevelXp; }
    public void setNextLevelXp(long nextLevelXp) { this.nextLevelXp = nextLevelXp; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }




}