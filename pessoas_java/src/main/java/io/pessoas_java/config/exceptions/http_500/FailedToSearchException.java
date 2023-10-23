package io.pessoas_java.config.exceptions.http_500;

import java.io.Serial;

public final class FailedToSearchException extends ErroInternoQualquerException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToSearchException() {
        super("Falha ao tentar pesquisar Pessoa no banco de dados.");
    }
}

