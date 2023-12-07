package io.micro_noticia.adapters.out;

import io.micro_noticia.adapters.out.mapper.NoticiaEntityMapper;
import io.micro_noticia.adapters.out.repository.NoticiaRepository;
import io.micro_noticia.application.core.domain.NoticiaBusiness;
import io.micro_noticia.application.ports.output.NoticiaSalvarOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class NoticiaSalvarAdapter implements NoticiaSalvarOutputPort {

    private final Logger log = Logger.getLogger(NoticiaSalvarAdapter.class.getName());

    private final NoticiaEntityMapper noticiaEntityMapper;

    private final NoticiaRepository noticiaRepository;

    @Transactional
    @Override
    public NoticiaBusiness salvar(NoticiaBusiness noticiaBusiness) {
        log.info("Adapter - iniciada etapa para salvar Notícia no banco de dados.");

        var resposta = Optional.of(noticiaBusiness)
            .map(this.noticiaEntityMapper::toNoticiaEntity)
            .map(this.noticiaRepository::save)
            .map(this.noticiaEntityMapper::toNoticiaBusiness)
            .orElseThrow();

        log.info("Adapter - concluída etapa para salvar Notícia no banco de dados.");

        return resposta;
    }
}

