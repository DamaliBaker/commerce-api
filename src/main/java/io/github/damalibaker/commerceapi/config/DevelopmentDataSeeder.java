package io.github.damalibaker.commerceapi.config;

import io.github.damalibaker.commerceapi.user.entity.UserEntity;
import io.github.damalibaker.commerceapi.user.enums.Role;
import io.github.damalibaker.commerceapi.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DevelopmentDataSeeder {
    @Bean
    public CommandLineRunner seedUsers(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.seed.admin-email:}") String adminEmail,
            @Value("${app.seed.admin-password:}") String adminPassword
    ) {
        return args -> {
            if (adminEmail.isBlank() || adminPassword.isBlank()) {
                return;
            }

            if (!userRepository.existsByEmail(adminEmail)) {
                UserEntity admin = UserEntity.builder()
                                .email(adminEmail)
                                .password(passwordEncoder.encode(adminPassword))
                                .role(Role.ROLE_ADMIN)
                                .build();
                userRepository.save(admin);
            }
        };
    }
}
