package io.pessoas_java.config.bean;

import io.pessoas_java.adapters.out.PessoaConsultarPorCpfAdapter;
import io.pessoas_java.application.core.domain.regras.RegraCpfUnico;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegrasConfig {

    @Bean
    public RegraCpfUnico regraCpfUnico(PessoaConsultarPorCpfAdapter pessoaConsultarPorCpfAdapter) {
        return new RegraCpfUnico(pessoaConsultarPorCpfAdapter);
    }
}

