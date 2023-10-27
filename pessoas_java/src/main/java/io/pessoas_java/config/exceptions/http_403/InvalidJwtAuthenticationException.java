package io.pessoas_java.config.exceptions.http_403;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public InvalidJwtAuthenticationException(String mensagem) {
        super(mensagem);
    }
}

