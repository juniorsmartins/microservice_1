package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.in.PessoaCadastrarInputPort;

public class PessoaCadastrarUseCase implements PessoaCadastrarInputPort {

    @Override
    public Pessoa cadastrar(Pessoa pessoa) {

        return pessoa;
    }
}

