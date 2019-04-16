package ru.homework.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.homework")
@EnableJpaRepositories(basePackages = "ru.homework.repositories")
@EntityScan(basePackages = "ru.homework.models")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
