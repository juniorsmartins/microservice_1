package io.pessoas_java.config.exceptions.http_500;

import java.io.Serial;

public final class FailedToSaveException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToSaveException(String mensagem) {
        super(mensagem);
    }

    public FailedToSaveException() {
        this("Failed when trying to save person to database.");
    }
}

