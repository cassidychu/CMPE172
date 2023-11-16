package com.example.springstarbucksapi;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StarbucksApiConfig {

    @Bean
    public Queue drinks() {
        return new Queue("drinks");
    }
}