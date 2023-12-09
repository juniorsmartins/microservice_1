package io.micronoticias.config.exception.http_400;

import io.micronoticias.config.MensagemPadrao;

public final class CampoVazioProibidoException extends DadoInvalidoException {

    public CampoVazioProibidoException(String nomeCampo) {
        super(String.format(MensagemPadrao.CAMPO_VAZIO_PROIBIDO, nomeCampo));
    }
}

