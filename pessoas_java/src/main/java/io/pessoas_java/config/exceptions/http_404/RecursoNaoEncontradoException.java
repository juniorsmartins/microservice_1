package io.pessoas_java.config.exceptions.http_404;

import java.io.Serial;

public abstract class RecursoNaoEncontradoException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public RecursoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }
}

