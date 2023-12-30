package io.micronoticias.config.bean;

import io.micronoticias.adapter.out.NoticiaPesquisarAdapter;
import io.micronoticias.adapter.out.NoticiaSalvarAdapter;
import io.micronoticias.application.core.usecase.NoticiaCriarUseCase;
import io.micronoticias.application.core.usecase.NoticiaPesquisarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoticiaUseCaseConfig {

    @Bean
    public NoticiaCriarUseCase noticiaCriarUseCase(NoticiaSalvarAdapter noticiaSalvarAdapter) {
        return new NoticiaCriarUseCase(noticiaSalvarAdapter);
    }

    @Bean
    public NoticiaPesquisarUseCase noticiaPesquisarUseCase(NoticiaPesquisarAdapter noticiaPesquisarAdapter) {
        return new NoticiaPesquisarUseCase(noticiaPesquisarAdapter);
    }
}

