package com.example.Rotina_Do_Heroi.modules.user.controller;

import com.example.Rotina_Do_Heroi.modules.user.dto.LoginDTO;
import com.example.Rotina_Do_Heroi.modules.user.entity.User;
import com.example.Rotina_Do_Heroi.modules.user.repository.UserRepository;
import com.example.Rotina_Do_Heroi.modules.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository; // Injetado para buscar o objeto User completo

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // ÚNICO MÉTODO DE LOGIN: Agora retorna o objeto User completo
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginData) {
        Optional<User> userOpt = userRepository.findByUsername(loginData.getUsername());

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginData.getPassword())) {
            // Retorna o herói com level, xp e todos os atributos para o React
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        }
    }

    @PostMapping("/{id}/gain-xp")
    public ResponseEntity<User> gainXp(@PathVariable Long id, @RequestBody Map<String, Long> body) {
        Long xpAmount = body.get("xp");
        User heroUpdated = userService.gainXp(id, xpAmount);
        return ResponseEntity.ok(heroUpdated);
    }
}