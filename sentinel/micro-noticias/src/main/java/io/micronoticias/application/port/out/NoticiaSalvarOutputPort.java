package io.micronoticias.application.port.out;

import io.micronoticias.application.core.domain.NoticiaBusiness;

public interface NoticiaSalvarOutputPort {

    NoticiaBusiness salvar(NoticiaBusiness noticiaBusiness);
}

