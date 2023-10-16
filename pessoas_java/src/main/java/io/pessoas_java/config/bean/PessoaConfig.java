package io.pessoas_java.config.bean;

import io.pessoas_java.adapters.out.PessoaCadastrarAdapter;
import io.pessoas_java.application.core.usecase.PessoaCadastrarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaConfig {

    @Bean
    public PessoaCadastrarUseCase pessoaCadastrarUseCase(PessoaCadastrarAdapter pessoaCadastrarAdapter) {
        return new PessoaCadastrarUseCase(pessoaCadastrarAdapter);
    }
}

