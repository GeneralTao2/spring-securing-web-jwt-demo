package com.example.securingweb.service;

import com.example.securingweb.model.Role;
import com.example.securingweb.model.User;
import com.example.securingweb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

// TODO:https://www.jenkins.io/
@Configuration
public class LoadDB {
    @Bean
    CommandLineRunner initDB(UserRepository userRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userRepository.saveAll(List.of(
                            new User(0L, "u", encoder.encode("u"), Role.USER),
                            new User(0L, "q", encoder.encode("q"), Role.USER),
                            new User(0L, "w", encoder.encode("w"), Role.ADMIN)
                    )
            );
        };
    }
}
