package io.pessoas_java.config.exceptions.http_400;

public final class CpfNaoUnicoException extends RequisicaoMalFormuladaException {

  public CpfNaoUnicoException(String cpf) {
    super(String.format("O CPF %s já existe no banco de dados.", cpf));
  }
}

