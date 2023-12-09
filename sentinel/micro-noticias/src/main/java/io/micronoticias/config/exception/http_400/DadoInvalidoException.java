package io.micronoticias.config.exception.http_400;

public abstract sealed class DadoInvalidoException extends RuntimeException permits CampoNuloProibidoException,
        DadoComTamanhoMaximoInvalidoException, CampoVazioProibidoException {

    DadoInvalidoException(String mensagem) {
        super(mensagem);
    }
}

