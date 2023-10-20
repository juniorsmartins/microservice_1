package io.pessoas_java.config.exceptions.http_409;

import java.io.Serial;

public abstract class RegraDeNegocioVioladaException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public RegraDeNegocioVioladaException(String mensagem) {
    super(mensagem);
  }
}

