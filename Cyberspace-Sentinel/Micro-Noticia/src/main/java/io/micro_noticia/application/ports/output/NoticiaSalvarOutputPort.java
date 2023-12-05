package io.micro_noticia.application.ports.output;

import io.micro_noticia.application.core.domain.NoticiaBusiness;

public interface NoticiaSalvarOutputPort {

    NoticiaBusiness salvar(NoticiaBusiness noticiaBusiness);
}

