package io.micronoticias.config.bean;

import io.micronoticias.adapter.out.NoticiaSalvarAdapter;
import io.micronoticias.application.core.usecase.NoticiaCriarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoticiaUseCaseConfig {

    @Bean
    public NoticiaCriarUseCase noticiaCriarUseCase(NoticiaSalvarAdapter noticiaSalvarAdapter) {
        return new NoticiaCriarUseCase(noticiaSalvarAdapter);
    }
}

