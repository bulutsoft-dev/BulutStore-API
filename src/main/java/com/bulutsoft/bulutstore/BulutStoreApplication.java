package com.bulutsoft.bulutstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BulutStoreApplication {

    public static void main(String[] args) {
        // Load .env-production file if present
        Dotenv.configure()
            .filename(".env-production")
            .ignoreIfMissing()
            .load();
        SpringApplication.run(BulutStoreApplication.class, args);
    }

}