package io.micronoticias.config.exception.http_400;

import io.micronoticias.config.MensagemPadrao;

public final class CampoNuloProibidoException extends DadoInvalidoException {

    public CampoNuloProibidoException(String nomeCampo) {
        super(String.format(MensagemPadrao.CAMPO_NULO_PROIBIDO, nomeCampo));
    }
}

