package io.pessoas_java.config.bean;

import io.pessoas_java.application.core.usecase.PessoaCadastrarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaConfig {

    @Bean
    public PessoaCadastrarUseCase pessoaCadastrarUseCase() {
        return new PessoaCadastrarUseCase();
    }
}

