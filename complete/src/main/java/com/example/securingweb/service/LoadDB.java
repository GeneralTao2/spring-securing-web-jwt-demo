package com.example.securingweb.service;

import com.example.securingweb.repository.UserRepository;
import com.example.securingweb.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// TODO:https://www.jenkins.io/
@Configuration
public class LoadDB {
    @Bean
    CommandLineRunner initDB(UserRepository userRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPassword = "u";
            String encodedPassword = encoder.encode(rawPassword);
            userRepository.save(
                    new User(0L, "u", encodedPassword, "ROLE USER")
            );
        };
    }
}
