package io.micronoticias.application.port.out;

import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.core.domain.filtro.NoticiaFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticiaPesquisarOutputPort {

    Page<NoticiaBusiness> pesquisar(NoticiaFiltro noticiaFiltro, Pageable paginacao);
}

