package io.pessoas_java.config.exceptions.http_404;

import java.io.Serial;
import java.util.UUID;

public final class PessoaNaoEncontradaPorChaveException extends RecursoNaoEncontradoException {

    public PessoaNaoEncontradaPorChaveException(String mensagem) {
        super(mensagem);
    }

    public PessoaNaoEncontradaPorChaveException(UUID chave) {
        this(String.format("Não encontrado recurso com a chave: %s", chave));
    }
}

