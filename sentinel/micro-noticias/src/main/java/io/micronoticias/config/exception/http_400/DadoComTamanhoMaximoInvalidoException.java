package io.micronoticias.config.exception.http_400;

import io.micronoticias.config.MensagemPadrao;

public final class DadoComTamanhoMaximoInvalidoException extends DadoInvalidoException {

    public DadoComTamanhoMaximoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public DadoComTamanhoMaximoInvalidoException(String nomeCampo, int limiteMaximo, int quantiaCaracteres) {
        super(String.format(MensagemPadrao.DADO_COM_TAMANHO_INVALIDO, nomeCampo, limiteMaximo, quantiaCaracteres));
    }
}

