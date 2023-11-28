package io.micro_texto.config.exception.http_400;

public abstract sealed class RequisicaoMalFormuladaException extends RuntimeException permits DadoComTamanhoInvalidoException, DadoComCampoNuloException {

    RequisicaoMalFormuladaException(String mensagem) {
        super(mensagem);
    }
}

