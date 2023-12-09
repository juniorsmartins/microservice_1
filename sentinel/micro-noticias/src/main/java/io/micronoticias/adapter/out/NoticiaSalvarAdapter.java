package io.micronoticias.adapter.out;

import io.micronoticias.adapter.mapper.NoticiaMapper;
import io.micronoticias.adapter.out.repository.NoticiaRepository;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.port.out.NoticiaSalvarOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoticiaSalvarAdapter implements NoticiaSalvarOutputPort {

    private final NoticiaRepository repository;

    private final NoticiaMapper mapper;

    @Transactional
    @Override
    public NoticiaBusiness salvar(NoticiaBusiness noticiaBusiness) {

        return Optional.of(noticiaBusiness)
                .map(this.mapper::toNoticiaEntity)
                .map(this.repository::save)
                .map(this.mapper::fromNoticiaEntity)
                .orElseThrow(NoSuchElementException::new);
    }
}

