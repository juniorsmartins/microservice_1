package io.micronoticias.application.port.in;

import io.micronoticias.application.core.domain.NoticiaBusiness;

public interface NoticiaCriarInputPort {

    NoticiaBusiness criar(NoticiaBusiness noticiaBusiness);
}

