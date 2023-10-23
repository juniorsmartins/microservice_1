package io.pessoas_java.config.exceptions.http_500;

import java.io.Serial;

public abstract class ErroInternoQualquerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ErroInternoQualquerException(String mensagem) {
        super(mensagem);
    }
}

