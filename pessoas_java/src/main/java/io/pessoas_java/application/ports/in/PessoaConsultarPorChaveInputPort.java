package io.pessoas_java.application.ports.in;

import io.pessoas_java.application.core.domain.Pessoa;

import java.util.UUID;

public interface PessoaConsultarPorChaveInputPort {

    Pessoa consultarPorChave(UUID chave);
}

