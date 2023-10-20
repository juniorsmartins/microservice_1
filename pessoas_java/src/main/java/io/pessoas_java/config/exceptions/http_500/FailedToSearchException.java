package io.pessoas_java.config.exceptions.http_500;

import java.io.Serial;

public final class FailedToSearchException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToSearchException(String mensagem) {
        super(mensagem);
    }

    public FailedToSearchException() {
        this("Failed when trying to search for people in the database.");
    }
}

