package com.ecommerce.auth.config;

import com.ecommerce.auth.entity.Role;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {

            String adminEmail = "admin@example.com";

            if (!userRepository.existsByEmail(adminEmail)) {

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User admin = User.builder()
                        .email(adminEmail)
                        .password(encoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(admin);

                System.out.println("✅ Default ADMIN user created");
            }
        };
    }
}
