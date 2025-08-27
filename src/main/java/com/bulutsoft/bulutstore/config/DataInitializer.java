package com.bulutsoft.bulutstore.config;

import com.bulutsoft.bulutstore.entity.Role;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.entity.UserStatus;
import com.bulutsoft.bulutstore.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123")) // Şifrenizi değiştirin!
                        .role(Role.ADMIN)
                        .status(UserStatus.ACTIVE)
                        .build();
                userRepository.save(admin);
                System.out.println("Admin kullanıcı oluşturuldu: admin / admin123");
            }
        };
    }
}

