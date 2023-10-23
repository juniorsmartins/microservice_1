package io.pessoas_java.config.exceptions.http_404;

import java.io.Serial;
import java.util.UUID;

public final class PessoaNaoEncontradaPorChaveException extends RecursoNaoEncontradoException {

    public PessoaNaoEncontradaPorChaveException(UUID chave) {
        super(String.format("Não encontrada Pessoa com a chave: %s", chave));
    }
}

