package io.pessoas_java.config.exceptions.http_404;

public final class UserNaoEncontradoPorUserNameException extends RecursoNaoEncontradoException {

    public UserNaoEncontradoPorUserNameException(String userName) {
        super(String.format("Não encontrado User com userName: %s", userName));
    }
}

