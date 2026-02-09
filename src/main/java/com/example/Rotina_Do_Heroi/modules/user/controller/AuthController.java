package com.example.Rotina_Do_Heroi.modules.user.controller;

import com.example.Rotina_Do_Heroi.modules.user.dto.LoginDTO;
import com.example.Rotina_Do_Heroi.modules.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginData) {
        // Por enquanto, apenas para testar a conexão
        System.out.println("Tentativa de login para: " + loginData.getUsername());
        return ResponseEntity.ok("Conexão com o backend da FASCODE estabelecida!");
    }
}