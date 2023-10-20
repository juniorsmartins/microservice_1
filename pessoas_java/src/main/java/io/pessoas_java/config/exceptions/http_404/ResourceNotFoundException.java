package io.pessoas_java.config.exceptions.http_404;

import java.io.Serial;
import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ResourceNotFoundException(UUID chave) {
        this(String.format("Não encontrado recurso com a chave: %s", chave));
    }
}

