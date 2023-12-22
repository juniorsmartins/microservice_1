package io.micronoticias.adapter.out;

import io.micronoticias.adapter.out.entity.NoticiaEntity;
import io.micronoticias.adapter.out.repository.NoticiaRepository;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.port.out.NoticiaSalvarOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoticiaSalvarAdapter implements NoticiaSalvarOutputPort {

    private final NoticiaRepository repository;

    @Transactional
    @Override
    public NoticiaBusiness salvar(NoticiaBusiness noticiaBusiness) {

        return Optional.ofNullable(noticiaBusiness)
                .map(NoticiaEntity::converterParaEntity)
                .map(this.repository::save)
                .map(NoticiaEntity::converterParaBusiness)
                .orElseThrow();
    }
}

