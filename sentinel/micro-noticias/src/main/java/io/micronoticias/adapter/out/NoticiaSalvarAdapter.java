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
                .map(not -> {
                    System.out.println("1 - " + not);
                    return not;
                })
                .map(this.mapper::toNoticiaEntity)
                .map(not -> {
                    System.out.println("2 - " + not);
                    return not;
                })
                .map(this.repository::save)
                .map(not -> {
                    System.out.println("3 - " + not);
                    return not;
                })
                .map(this.mapper::fromNoticiaEntity)
                .map(not -> {
                    System.out.println("4 - " + not);
                    return not;
                })
                .orElseThrow(NoSuchElementException::new);
    }
}

