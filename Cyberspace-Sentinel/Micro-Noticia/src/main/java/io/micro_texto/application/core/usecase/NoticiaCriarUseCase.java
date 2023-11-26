package io.micro_texto.application.core.usecase;

import io.micro_texto.application.core.domain.NoticiaBusiness;
import io.micro_texto.application.ports.input.NoticiaCriarInputPort;
import io.micro_texto.application.ports.output.NoticiaSalvarOutputPort;

import java.util.Optional;
import java.util.logging.Logger;

public class NoticiaCriarUseCase implements NoticiaCriarInputPort {

    private final Logger log = Logger.getLogger(NoticiaCriarUseCase.class.getName());

    private final NoticiaSalvarOutputPort noticiaSalvarOutputPort;

    public NoticiaCriarUseCase(NoticiaSalvarOutputPort noticiaSalvarOutputPort) {
        this.noticiaSalvarOutputPort = noticiaSalvarOutputPort;
    }

    @Override
    public NoticiaBusiness criar(NoticiaBusiness noticia) {
        log.info("UseCase - iniciado processamento de regras de negócio para Criar Notícia.");

        var resposta = Optional.of(noticia)
            .map(this.noticiaSalvarOutputPort::salvar)
            .orElseThrow();

        log.info("UseCase - finalizado processamento de regras de negócio para Criar Notícia.");

        return resposta;
    }
}

