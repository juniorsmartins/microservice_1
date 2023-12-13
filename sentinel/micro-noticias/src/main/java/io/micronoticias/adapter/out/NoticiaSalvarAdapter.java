package io.micronoticias.adapter.out;

import io.micronoticias.adapter.out.mapper.NoticiaMapperEntity;
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

    private final NoticiaMapperEntity mapper;

    @Transactional
    @Override
    public NoticiaBusiness salvar(NoticiaBusiness noticiaBusiness) {

        return Optional.of(noticiaBusiness)
                .map(teste -> {
                    System.out.println(teste);
                    return teste;
                })
                .map(this.mapper::toNoticiaEntity)
                .map(teste -> {
                    System.out.println(teste);
                    return teste;
                })
                .map(this.repository::save)
                .map(teste -> {
                    System.out.println(teste);
                    return teste;
                })
                .map(this.mapper::toNoticiaBusiness)
                .orElseThrow(NoSuchElementException::new);
    }
}

