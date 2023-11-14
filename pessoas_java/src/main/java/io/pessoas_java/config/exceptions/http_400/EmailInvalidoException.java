package io.pessoas_java.config.exceptions.http_400;

import java.io.Serial;

public final class EmailInvalidoException extends RequisicaoMalFormuladaException {

  @Serial
  private static final long serialVersionUID = 1L;

  public EmailInvalidoException(String email) {
    super(String.format("Email inválido: %s", email));
  }
}

