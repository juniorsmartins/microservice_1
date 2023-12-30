package io.micronoticias.config.exception.http_400;

public final class NoticiaPesquisarUseCaseException extends RequisicaoMalFormuladaException {

    public NoticiaPesquisarUseCaseException(String mensagem) {
        super(mensagem);
    }

    public NoticiaPesquisarUseCaseException() {
        this("Falha no serviço de pesquisar Notícia.");
    }
}

