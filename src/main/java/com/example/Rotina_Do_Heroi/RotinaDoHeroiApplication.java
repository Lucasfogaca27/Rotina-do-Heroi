package com.example.Rotina_Do_Heroi;

import com.example.Rotina_Do_Heroi.modules.user.entity.User;
import com.example.Rotina_Do_Heroi.modules.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RotinaDoHeroiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RotinaDoHeroiApplication.class, args);



	}

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            if (repository.count() == 0) { // Só cria se o banco estiver vazio
                User heroi = new User();
                heroi.setUsername("HEROO27");
                heroi.setPassword("123");
                heroi.setEmail("lucas@fascode.com");

                // Atributos iniciais manuais
                heroi.setLevel(1);
                heroi.setCurrentXp(0L);
                heroi.setNextLevelXp(100L);

                repository.save(heroi);
                System.out.println(">>> Herói HEROO27 semeado com sucesso no H2!");
            }
        };
    }

}
