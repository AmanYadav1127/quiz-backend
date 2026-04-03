package com.quiz.Quiz.Application.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // Nayi RestTemplate Bean (ML Service ke liye)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
