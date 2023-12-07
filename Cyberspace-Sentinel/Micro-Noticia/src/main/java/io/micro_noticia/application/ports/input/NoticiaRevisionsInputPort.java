package io.micro_noticia.application.ports.input;

import java.util.List;

public interface NoticiaRevisionsInputPort {

    List<String> listarRevisoesPorId(final Long id);
}

