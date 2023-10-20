package io.pessoas_java.application.ports.out;

import io.pessoas_java.application.core.domain.Pessoa;

import java.util.UUID;

public interface PessoaConsultarPorChaveOutputPort {

    Pessoa consultarPorChave(UUID chave);
}

