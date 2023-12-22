package io.micronoticias.application.core.usecase;

import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.port.in.NoticiaCriarInputPort;
import io.micronoticias.application.port.out.NoticiaSalvarOutputPort;

import java.util.Optional;

public class NoticiaCriarUseCase implements NoticiaCriarInputPort {

    private final NoticiaSalvarOutputPort salvarOutputPort;

    public NoticiaCriarUseCase(NoticiaSalvarOutputPort salvarOutputPort) {
        this.salvarOutputPort = salvarOutputPort;
    }

    @Override
    public NoticiaBusiness criar(NoticiaBusiness noticiaBusiness) {

        return Optional.ofNullable(noticiaBusiness)
                .map(this.salvarOutputPort::salvar)
                .orElseThrow();
    }
}

