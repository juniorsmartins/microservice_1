package io.pessoas_java.application.ports.in;

import io.pessoas_java.application.core.domain.Pessoa;

public interface PessoaCadastrarInputPort {

    Pessoa cadastrar(Pessoa pessoa);
}

