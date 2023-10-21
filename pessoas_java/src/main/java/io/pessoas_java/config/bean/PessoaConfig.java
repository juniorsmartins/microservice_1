package io.pessoas_java.config.bean;

import io.pessoas_java.adapters.out.*;
import io.pessoas_java.application.core.usecase.PessoaCadastrarUseCase;
import io.pessoas_java.application.core.usecase.PessoaConsultarPorChaveUseCase;
import io.pessoas_java.application.core.usecase.PessoaDeletarPorChaveUseCase;
import io.pessoas_java.application.core.usecase.PessoaPesquisarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaConfig {

    @Bean
    public PessoaCadastrarUseCase pessoaCadastrarUseCase(PessoaSalvarAdapter pessoaSalvarAdapter,
                                                         PessoaConsultarPorCpfAdapter pessoaExistsByCpfAdapter) {
        return new PessoaCadastrarUseCase(pessoaSalvarAdapter, pessoaExistsByCpfAdapter);
    }

    @Bean
    public PessoaPesquisarUseCase pessoaPesquisarUseCase(PessoaPesquisarAdapter pessoaPesquisarAdapter) {
        return new PessoaPesquisarUseCase(pessoaPesquisarAdapter);
    }

    @Bean
    public PessoaConsultarPorChaveUseCase pessoaConsultarPorChaveUseCase(PessoaConsultarPorChaveAdapter pessoaConsultarPorChaveAdapter) {
        return new PessoaConsultarPorChaveUseCase(pessoaConsultarPorChaveAdapter);
    }

    @Bean
    public PessoaDeletarPorChaveUseCase pessoaDeletarPorChaveUseCase(PessoaConsultarPorChaveAdapter pessoaConsultarPorChaveAdapter,
                                                                     PessoaDeletarAdapter pessoaDeletarPorChaveAdapter) {
        return new PessoaDeletarPorChaveUseCase(pessoaConsultarPorChaveAdapter, pessoaDeletarPorChaveAdapter);
    }
}

