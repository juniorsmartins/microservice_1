package io.pessoas_java.config.exceptions.http_409;

public final class RegraCpfUnicoException extends RegraDeNegocioVioladaException {

  public RegraCpfUnicoException(String cpf) {
    super(String.format("O CPF %s já está em uso.", cpf));
  }
}

