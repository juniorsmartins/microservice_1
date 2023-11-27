package io.micro_texto.application.ports.input;

import java.util.List;

public interface NoticiaRevisionsInputPort {

    List<String> listarRevisoesPorId(final Long id);
}

