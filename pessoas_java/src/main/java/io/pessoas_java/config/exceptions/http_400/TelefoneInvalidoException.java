package io.pessoas_java.config.exceptions.http_400;

import java.io.Serial;

public final class TelefoneInvalidoException extends RequisicaoMalFormuladaException {

  @Serial
  private static final long serialVersionUID = 1L;

  public TelefoneInvalidoException(String numeroTelefone) {
    super(String.format("Número inválido de telefone: %s", numeroTelefone));
  }
}

