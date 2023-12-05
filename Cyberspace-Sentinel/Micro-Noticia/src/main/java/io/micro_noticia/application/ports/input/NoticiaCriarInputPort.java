package io.micro_noticia.application.ports.input;

import io.micro_noticia.application.core.domain.NoticiaBusiness;

public interface NoticiaCriarInputPort {

    NoticiaBusiness criar(NoticiaBusiness noticia);
}

