package io.micro_texto.config.bean;

import io.micro_texto.adapters.out.NoticiaSalvarAdapter;
import io.micro_texto.application.core.usecase.NoticiaCriarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoticiaUseCaseConfig {

    @Bean
    public NoticiaCriarUseCase noticiaCriarUseCase(NoticiaSalvarAdapter noticiaSalvarAdapter) {
        return new NoticiaCriarUseCase(noticiaSalvarAdapter);
    }
}

