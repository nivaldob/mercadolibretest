package com.example.demo.application.config;

import com.example.demo.domain.usecase.ValidatorUseCase;
import com.example.demo.infraestructure.drivenadapter.ValidatorRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class Beans {

    @Bean
    //@DependsOn("ValidatorRepositoryAdapter")
    public ValidatorUseCase configureValidatorUseCase(ValidatorRepositoryAdapter repository) {
        return new ValidatorUseCase(repository);
    }

}
