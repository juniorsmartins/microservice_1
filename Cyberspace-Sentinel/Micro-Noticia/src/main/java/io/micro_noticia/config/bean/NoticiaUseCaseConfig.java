package io.micro_noticia.config.bean;

import io.micro_noticia.adapters.out.NoticiaRevisionsAdapter;
import io.micro_noticia.adapters.out.NoticiaSalvarAdapter;
import io.micro_noticia.application.core.usecase.NoticiaCriarUseCase;
import io.micro_noticia.application.core.usecase.NoticiaRevisionsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoticiaUseCaseConfig {

    @Bean
    public NoticiaCriarUseCase noticiaCriarUseCase(NoticiaSalvarAdapter noticiaSalvarAdapter) {
        return new NoticiaCriarUseCase(noticiaSalvarAdapter);
    }

    @Bean
    public NoticiaRevisionsUseCase noticiaRevisionsUseCase(NoticiaRevisionsAdapter noticiaRevisionsAdapter) {
        return new NoticiaRevisionsUseCase(noticiaRevisionsAdapter);
    }
}

