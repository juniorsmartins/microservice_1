package io.micro_texto.application.core.usecase;

import io.micro_texto.application.ports.input.NoticiaRevisionsInputPort;
import io.micro_texto.application.ports.output.NoticiaRevisionsOutputPort;

import java.util.List;

public class NoticiaRevisionsUseCase implements NoticiaRevisionsInputPort {

    private final NoticiaRevisionsOutputPort noticiaRevisionsOutputPort;

    public NoticiaRevisionsUseCase(NoticiaRevisionsOutputPort noticiaRevisionsOutputPort) {
        this.noticiaRevisionsOutputPort = noticiaRevisionsOutputPort;
    }

    @Override
    public List<String> listarRevisoesPorId(final Long id) {
        return this.noticiaRevisionsOutputPort.listarRevisoesPorId(id);
    }
}

