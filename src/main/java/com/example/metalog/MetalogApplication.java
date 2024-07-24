package com.example.metalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MetalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetalogApplication.class, args);
    }

}
