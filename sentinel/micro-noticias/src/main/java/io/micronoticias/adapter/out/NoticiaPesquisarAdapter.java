package io.micronoticias.adapter.out;

import io.micronoticias.adapter.out.entity.NoticiaEntity;
import io.micronoticias.adapter.out.repository.NoticiaRepository;
import io.micronoticias.adapter.out.specification.NoticiaSpecification;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.core.domain.filtro.NoticiaFiltro;
import io.micronoticias.application.core.usecase.NoticiaPesquisarUseCase;
import io.micronoticias.application.port.out.NoticiaPesquisarOutputPort;
import io.micronoticias.config.exception.http_400.NoticiaPesquisarAdapterException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoticiaPesquisarAdapter implements NoticiaPesquisarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(NoticiaPesquisarAdapter.class);

    private final NoticiaRepository noticiaRepository;

    @Override
    public Page<NoticiaBusiness> pesquisar(final NoticiaFiltro noticiaFiltro, final Pageable paginacao) {

        log.info("Iniciado adaptador para pesquisar Notícias.");

        var resposta = Optional.ofNullable(noticiaFiltro)
            .map(parametros -> this.noticiaRepository
                    .findAll(NoticiaSpecification.consultarDinamicamente(parametros), paginacao))
            .map(pagina -> pagina.map(NoticiaEntity::converterParaBusiness))
            .orElseThrow(NoticiaPesquisarAdapterException::new);

        log.info("Finalizado adaptador para pesquisar Notícias.");

        return resposta;
    }
}

