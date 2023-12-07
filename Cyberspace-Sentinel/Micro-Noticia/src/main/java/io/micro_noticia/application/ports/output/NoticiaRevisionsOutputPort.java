package io.micro_noticia.application.ports.output;

import java.util.List;

public interface NoticiaRevisionsOutputPort {

    List<String> listarRevisoesPorId(Long id);
}

