package io.micronoticias.config.exception.http_400;

public final class NoticiaPesquisarAdapterException extends RequisicaoMalFormuladaException {

    public NoticiaPesquisarAdapterException(String mensagem) {
        super(mensagem);
    }

    public NoticiaPesquisarAdapterException() {
        this("Falha ao tentar pesquisar Notícia no banco de dados.");
    }
}

