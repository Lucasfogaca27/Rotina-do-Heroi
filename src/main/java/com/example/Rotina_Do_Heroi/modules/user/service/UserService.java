package com.example.Rotina_Do_Heroi.modules.user.service;

import com.example.Rotina_Do_Heroi.modules.user.entity.User;
import com.example.Rotina_Do_Heroi.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método para autenticar o Login
    public boolean autenticar(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }


    // Método para criar seu herói inicial
    public User createHero(User user) {
        // Lógica inicial para novos heróis
        user.setLevel(1);
        user.setCurrentXp(0L);
        user.setNextLevelXp(100L);
        return userRepository.save(user);
    }

    // Lógica de Ganho de XP
    public User gainXp(Long userId, Long xpAmount) {
        User hero = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Herói não encontrado!"));

        hero.setCurrentXp(hero.getCurrentXp() + xpAmount);

        // Lógica simples de Level Up
        if (hero.getCurrentXp() >= hero.getNextLevelXp()) {
            levelUp(hero);
        }

        return userRepository.save(hero);
    }

    private void levelUp(User hero) {
        hero.setLevel(hero.getLevel() + 1);
        hero.setCurrentXp(0L);
        // Aumenta a dificuldade para o próximo nível (ex: +50%)
        hero.setNextLevelXp((long) (hero.getNextLevelXp() * 1.5));
        System.out.println("LEVEL UP! Agora você é nível " + hero.getLevel());
    }
}