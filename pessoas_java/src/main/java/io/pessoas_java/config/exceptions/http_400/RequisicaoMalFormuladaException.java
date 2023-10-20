package io.pessoas_java.config.exceptions.http_400;

import java.io.Serial;

public abstract class RequisicaoMalFormuladaException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public RequisicaoMalFormuladaException(String mensagem) {
    super(mensagem);
  }
}

