package io.micro_noticia.adapters.out;

import io.micro_noticia.adapters.out.repository.NoticiaRepository;
import io.micro_noticia.application.ports.output.NoticiaRevisionsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticiaRevisionsAdapter implements NoticiaRevisionsOutputPort {

    private final NoticiaRepository noticiaRepository;

    @Override
    public List<String> listarRevisoesPorId(final Long id) {

        return this.noticiaRepository.findRevisions(id)
            .stream()
            .map(Object::toString)
            .toList();
    }
}

