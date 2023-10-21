package io.pessoas_java.config.bean;

import io.pessoas_java.adapters.out.*;
import io.pessoas_java.application.core.domain.regras.RegraCpfUnico;
import io.pessoas_java.application.core.domain.regras.RegrasEditar;
import io.pessoas_java.application.core.usecase.*;
import io.pessoas_java.application.ports.out.PessoaSalvarOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    @Bean
    public PessoaEditarUseCase pessoaEditarUseCase(PessoaEditarAdapter pessoaEditarAdapter,
                                                   RegraCpfUnico regraCpfUnico) {
        return new PessoaEditarUseCase(pessoaEditarAdapter, regraCpfUnico);
    }
}

