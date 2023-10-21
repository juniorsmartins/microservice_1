package io.pessoas_java.config.exceptions.http_500;

import java.io.Serial;

public final class FailedToEditException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToEditException(String mensagem) {
        super(mensagem);
    }

    public FailedToEditException() {
        this("Failed when trying to edit person to database.");
    }
}

