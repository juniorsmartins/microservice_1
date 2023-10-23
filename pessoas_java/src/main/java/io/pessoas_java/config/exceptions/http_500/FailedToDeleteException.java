package io.pessoas_java.config.exceptions.http_500;

import java.io.Serial;

public final class FailedToDeleteException extends ErroInternoQualquerException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToDeleteException() {
        super("Falha ao tentar apagar Pessoa do banco de dados.");
    }
}

