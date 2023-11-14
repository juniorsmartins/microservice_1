package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.in.PessoaConsultarPorChaveInputPort;
import io.pessoas_java.application.ports.out.PessoaConsultarPorChaveOutputPort;

import java.util.UUID;
import java.util.logging.Logger;

public class PessoaConsultarPorChaveUseCase implements PessoaConsultarPorChaveInputPort {

    private final Logger logger = Logger.getLogger(PessoaConsultarPorChaveUseCase.class.getName());

    private final PessoaConsultarPorChaveOutputPort pessoaConsultarPorChaveOutputPort;

    public PessoaConsultarPorChaveUseCase(PessoaConsultarPorChaveOutputPort pessoaConsultarPorChaveOutputPort) {
        this.pessoaConsultarPorChaveOutputPort = pessoaConsultarPorChaveOutputPort;
    }

    @Override
    public Pessoa consultarPorChave(final UUID chave) {

        logger.info("UseCase - iniciado serviço de consultar pessoa por chave.");

        var pessoaEncontrada = this.pessoaConsultarPorChaveOutputPort.consultarPorChave(chave);

        logger.info("UseCase - finalizado serviço de consultar pessoa por chave.");

        return pessoaEncontrada;
    }
}

