package io.pessoas_java.config.exceptions.http_500;

import java.io.Serial;

public final class FailedToSaveException extends ErroInternoQualquerException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToSaveException() {
        super("Falha ao tentar salvar no banco de dados.");
    }
}

