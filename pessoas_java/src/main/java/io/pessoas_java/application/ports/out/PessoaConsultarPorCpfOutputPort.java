package io.pessoas_java.application.ports.out;

import io.pessoas_java.application.core.domain.Pessoa;

import java.util.Optional;

public interface PessoaConsultarPorCpfOutputPort {

    Optional<Pessoa> consultarPorCpf(String cpf);
}

