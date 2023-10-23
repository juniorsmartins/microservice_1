package io.pessoas_java.config.exceptions.http_404;

public final class PessoaNaoEncontradaPorCpfException extends RecursoNaoEncontradoException {

    public PessoaNaoEncontradaPorCpfException(String cpf) {
        super(String.format("Não encontrada Pessoa com CPF: %s", cpf));
    }
}

