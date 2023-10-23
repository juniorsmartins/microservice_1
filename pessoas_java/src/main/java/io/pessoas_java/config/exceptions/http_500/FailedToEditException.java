package io.pessoas_java.config.exceptions.http_500;

import java.io.Serial;

public final class FailedToEditException extends ErroInternoQualquerException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToEditException() {
        super("Falha ao tentar editar Pessoa do banco de dados.");
    }
}

