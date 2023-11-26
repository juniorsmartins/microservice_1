package io.micro_texto.application.ports.output;

import io.micro_texto.application.core.domain.NoticiaBusiness;

public interface NoticiaSalvarOutputPort {

    NoticiaBusiness salvar(NoticiaBusiness noticiaBusiness);
}

