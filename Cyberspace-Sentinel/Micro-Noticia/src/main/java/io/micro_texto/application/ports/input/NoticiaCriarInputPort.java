package io.micro_texto.application.ports.input;

import io.micro_texto.application.core.domain.NoticiaBusiness;

public interface NoticiaCriarInputPort {

    NoticiaBusiness criar(NoticiaBusiness noticia);
}

