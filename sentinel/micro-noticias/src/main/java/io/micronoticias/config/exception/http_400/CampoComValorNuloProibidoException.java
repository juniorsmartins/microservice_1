package io.micronoticias.config.exception.http_400;

import io.micronoticias.config.MensagemPadrao;

public final class CampoComValorNuloProibidoException extends DadoInvalidoException {

    public CampoComValorNuloProibidoException(String nomeCampo) {
        super(String.format(MensagemPadrao.CAMPO_COM_VALOR_NULO_PROIBIDO, nomeCampo));
    }
}

