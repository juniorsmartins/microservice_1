package io.pessoas_java.config.exceptions.http_404;

public final class UsuarioNaoEncontradoPorUsernameException extends RecursoNaoEncontradoException {

    public UsuarioNaoEncontradoPorUsernameException(String username) {
        super(String.format("Não encontrado Usuário com Username: %s", username));
    }
}

