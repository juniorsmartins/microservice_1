package io.pessoas_java.config.bean;

import io.pessoas_java.adapters.out.*;
import io.pessoas_java.application.core.domain.regras.RegraCpfUnico;
import io.pessoas_java.application.core.domain.utils.UtilImpl;
import io.pessoas_java.application.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PessoaConfig {

    @Bean
    public PessoaCadastrarUseCase pessoaCadastrarUseCase(PessoaSalvarAdapter pessoaSalvarAdapter,
                                                         RegraCpfUnico regraCpfUnico,
                                                         PasswordEncoder passwordEncoder,
                                                         UtilImpl utilImpl) {
        return new PessoaCadastrarUseCase(pessoaSalvarAdapter, regraCpfUnico, passwordEncoder, utilImpl);
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
                                                                     PessoaDeletarPorIdAdapter pessoaDeletarPorChaveAdapter) {
        return new PessoaDeletarPorChaveUseCase(pessoaConsultarPorChaveAdapter, pessoaDeletarPorChaveAdapter);
    }

    @Bean
    public PessoaEditarUseCase pessoaEditarUseCase(PessoaEditarAdapter pessoaEditarAdapter,
                                                   RegraCpfUnico regraCpfUnico,
                                                   PasswordEncoder passwordEncoder,
                                                   UtilImpl utilImpl) {
        return new PessoaEditarUseCase(pessoaEditarAdapter, regraCpfUnico, passwordEncoder, utilImpl);
    }
}

