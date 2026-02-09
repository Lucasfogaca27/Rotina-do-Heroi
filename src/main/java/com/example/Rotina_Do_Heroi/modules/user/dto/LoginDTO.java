package com.example.Rotina_Do_Heroi.modules.user.dto;

public class LoginDTO {
    private String username;
    private String password;

    // Construtor vazio é OBRIGATÓRIO para o Jackson funcionar
    public LoginDTO() {}

    // GETTERS E SETTERS MANUAIS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}