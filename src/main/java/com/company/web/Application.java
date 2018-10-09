package com.company.web;

import com.company.data.AirplaneRepository;
import com.company.data.AirportRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.company.data")
@EntityScan( basePackages = {"com.company.domain"} )
@ComponentScan({"com.company.service", "com.company.web.auth", "com.company.web"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
