package com.bulutsoft.bulutstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BulutStoreApplication {

    public static void main(String[] args) {
        // Load .env-production file if present
        Dotenv dotenv = Dotenv.configure()
            .filename(".env-production")
            .ignoreIfMissing()
            .load();
        // Set all env variables as system properties for Spring Boot
        dotenv.entries().forEach(entry -> {
            if (System.getProperty(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
        SpringApplication.run(BulutStoreApplication.class, args);
    }

}