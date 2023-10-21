package io.pessoas_java.application.ports.out;

import io.pessoas_java.application.core.domain.Pessoa;

import java.util.Optional;
import java.util.UUID;

public interface PessoaConsultarPorChaveOutputPort {

    Optional<Pessoa> consultarPorChave(UUID chave);
}

