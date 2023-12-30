package io.micronoticias.application.core.usecase;

import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.port.in.NoticiaCriarInputPort;
import io.micronoticias.application.port.out.NoticiaSalvarOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class NoticiaCriarUseCase implements NoticiaCriarInputPort {

    private static final Logger log = LoggerFactory.getLogger(NoticiaCriarUseCase.class);

    private final NoticiaSalvarOutputPort salvarOutputPort;

    public NoticiaCriarUseCase(NoticiaSalvarOutputPort salvarOutputPort) {
        this.salvarOutputPort = salvarOutputPort;
    }

    @Override
    public NoticiaBusiness criar(NoticiaBusiness noticiaBusiness) {

        log.info("Iniciado serviço para criar Produto: {}", noticiaBusiness.getTitulo());

        var resposta = Optional.ofNullable(noticiaBusiness)
                .map(this.salvarOutputPort::salvar)
                .orElseThrow();

        log.info("Finalizado serviço para criar Notícia: {}", resposta.getTitulo());

        return resposta;
    }
}

