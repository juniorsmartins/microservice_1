package io.micro_noticia.config.exception.http_400;

public abstract sealed class DadoInvalidoException extends RuntimeException permits DadoComTamanhoInvalidoException, DadoComCampoNuloException {

    DadoInvalidoException(String mensagem) {
        super(mensagem);
    }
}

