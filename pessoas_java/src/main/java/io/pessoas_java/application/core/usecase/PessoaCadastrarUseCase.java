package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.in.PessoaCadastrarInputPort;
import io.pessoas_java.application.ports.out.PessoaCadastrarOutputPort;

public class PessoaCadastrarUseCase implements PessoaCadastrarInputPort {

    private final PessoaCadastrarOutputPort pessoaCadastrarOutputPort;

    public PessoaCadastrarUseCase(PessoaCadastrarOutputPort pessoaCadastrarOutputPort) {
        this.pessoaCadastrarOutputPort = pessoaCadastrarOutputPort;
    }

    @Override
    public Pessoa cadastrar(Pessoa pessoa) {

        return pessoa;
    }
}

