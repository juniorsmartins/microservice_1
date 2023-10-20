package io.pessoas_java.config.bean;

import io.pessoas_java.adapters.out.PessoaCadastrarAdapter;
import io.pessoas_java.adapters.out.PessoaConsultarPorChaveAdapter;
import io.pessoas_java.adapters.out.PessoaPesquisarAdapter;
import io.pessoas_java.application.core.usecase.PessoaCadastrarUseCase;
import io.pessoas_java.application.core.usecase.PessoaConsultarPorChaveUseCase;
import io.pessoas_java.application.core.usecase.PessoaPesquisarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaConfig {

    @Bean
    public PessoaCadastrarUseCase pessoaCadastrarUseCase(PessoaCadastrarAdapter pessoaCadastrarAdapter) {
        return new PessoaCadastrarUseCase(pessoaCadastrarAdapter);
    }

    @Bean
    public PessoaPesquisarUseCase pessoaPesquisarUseCase(PessoaPesquisarAdapter pessoaPesquisarAdapter) {
        return new PessoaPesquisarUseCase(pessoaPesquisarAdapter);
    }

    @Bean
    public PessoaConsultarPorChaveUseCase pessoaConsultarPorChaveUseCase(PessoaConsultarPorChaveAdapter pessoaConsultarPorChaveAdapter) {
        return new PessoaConsultarPorChaveUseCase(pessoaConsultarPorChaveAdapter);
    }
}

