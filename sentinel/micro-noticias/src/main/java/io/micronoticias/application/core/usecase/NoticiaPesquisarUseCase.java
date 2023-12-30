package io.micronoticias.application.core.usecase;

import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.core.domain.filtro.NoticiaFiltro;
import io.micronoticias.application.port.in.NoticiaPesquisarInputPort;
import io.micronoticias.application.port.out.NoticiaPesquisarOutputPort;
import io.micronoticias.config.exception.http_400.NoticiaPesquisarUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class NoticiaPesquisarUseCase implements NoticiaPesquisarInputPort {

    private static final Logger log = LoggerFactory.getLogger(NoticiaPesquisarUseCase.class);

    private final NoticiaPesquisarOutputPort noticiaPesquisarOutputPort;

    public NoticiaPesquisarUseCase(NoticiaPesquisarOutputPort noticiaPesquisarOutputPort) {
        this.noticiaPesquisarOutputPort = noticiaPesquisarOutputPort;
    }

    @Override
    public Page<NoticiaBusiness> pesquisar(final NoticiaFiltro noticiaFiltro, final Pageable paginacao) {

        log.info("Iniciado serviço para pesquisar Produto.");

        var resposta = Optional.ofNullable(noticiaFiltro)
            .map(filtro -> this.noticiaPesquisarOutputPort.pesquisar(filtro, paginacao))
            .orElseThrow(NoticiaPesquisarUseCaseException::new);

        log.info("Finalizado serviço para pesquisar Produto.");

        return resposta;
    }
}

