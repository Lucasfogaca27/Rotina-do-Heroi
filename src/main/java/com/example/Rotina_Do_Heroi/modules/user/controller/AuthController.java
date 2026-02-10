package com.example.Rotina_Do_Heroi.modules.user.controller;

import com.example.Rotina_Do_Heroi.modules.user.dto.LoginDTO;
import com.example.Rotina_Do_Heroi.modules.user.entity.User;
import com.example.Rotina_Do_Heroi.modules.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Necessário para o React não ser bloqueado
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Login Real: Agora valida o usuário e senha
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginData) {
        boolean autenticado = userService.autenticar(loginData.getUsername(), loginData.getPassword());

        if (autenticado) {
            return ResponseEntity.ok("Login realizado com sucesso! Bem-vindo, " + loginData.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        }
    }

    // Endpoint para ganhar XP: Teste a evolução do herói
    @PostMapping("/{id}/gain-xp")
    public ResponseEntity<User> gainXp(@PathVariable Long id, @RequestBody Map<String, Long> body) {
        Long xpAmount = body.get("xp");
        User heroUpdated = userService.gainXp(id, xpAmount);
        return ResponseEntity.ok(heroUpdated);
    }
}