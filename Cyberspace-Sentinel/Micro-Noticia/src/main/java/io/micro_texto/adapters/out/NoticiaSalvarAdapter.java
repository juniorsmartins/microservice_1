package io.micro_texto.adapters.out;

import io.micro_texto.adapters.out.repository.NoticiaRepository;
import io.micro_texto.application.core.domain.NoticiaBusiness;
import io.micro_texto.application.ports.output.NoticiaSalvarOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class NoticiaSalvarAdapter implements NoticiaSalvarOutputPort {

    private final NoticiaRepository noticiaRepository;

    @Transactional
    @Override
    public NoticiaBusiness salvar(NoticiaBusiness noticiaBusiness) {

        return null;
    }
}

