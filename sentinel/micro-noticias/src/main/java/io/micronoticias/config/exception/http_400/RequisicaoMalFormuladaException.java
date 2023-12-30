package io.micronoticias.config.exception.http_400;

import java.io.Serial;

public abstract sealed class RequisicaoMalFormuladaException extends RuntimeException
        permits NoticiaPesquisarUseCaseException, NoticiaPesquisarAdapterException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected RequisicaoMalFormuladaException(String mensagem) {
    super(mensagem);
  }
}

