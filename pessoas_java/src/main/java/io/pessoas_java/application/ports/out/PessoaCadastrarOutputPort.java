package io.pessoas_java.application.ports.out;

import io.pessoas_java.application.core.domain.Pessoa;

public interface PessoaCadastrarOutputPort {

    Pessoa salvar(Pessoa pessoa);
}

