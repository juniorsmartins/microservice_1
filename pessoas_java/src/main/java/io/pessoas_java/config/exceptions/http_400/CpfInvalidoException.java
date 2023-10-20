package io.pessoas_java.config.exceptions.http_400;

public final class CpfInvalidoException extends RequisicaoMalFormuladaException {

  public CpfInvalidoException(String cpf) {
    super(String.format("O número do CPF %s é inválido.", cpf));
  }
}

