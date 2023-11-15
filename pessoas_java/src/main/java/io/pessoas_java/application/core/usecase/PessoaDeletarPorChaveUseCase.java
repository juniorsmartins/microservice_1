package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.ports.in.PessoaDeletarPorChaveInputPort;
import io.pessoas_java.application.ports.out.PessoaDeletarPorChaveOutputPort;
import io.pessoas_java.config.exceptions.http_400.RequiredObjectIsNullException;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class PessoaDeletarPorChaveUseCase implements PessoaDeletarPorChaveInputPort {

    private final Logger logger = Logger.getLogger(PessoaDeletarPorChaveUseCase.class.getName());

    private final PessoaDeletarPorChaveOutputPort pessoaDeletarPorChaveOutputPort;

    public PessoaDeletarPorChaveUseCase(PessoaDeletarPorChaveOutputPort pessoaDeletarPorChaveOutputPort) {
        this.pessoaDeletarPorChaveOutputPort = pessoaDeletarPorChaveOutputPort;
    }

    @Override
    public void deletar(final UUID chave) {

        logger.info("UseCase - iniciado serviço de deletar uma pessoa por chave.");

        Optional.ofNullable(chave)
            .ifPresentOrElse(
                    this.pessoaDeletarPorChaveOutputPort::deletarPorChave,
                () -> {throw new RequiredObjectIsNullException();}
            );

        logger.info("UseCase - finalizado serviço de deletar uma pessoa por chave.");
    }
}

