package io.micro_texto.config.exception.http_400;

public abstract sealed class DadoInvalidoException extends RuntimeException permits DadoComTamanhoInvalidoException, DadoComCampoNuloException {

    DadoInvalidoException(String mensagem) {
        super(mensagem);
    }
}

