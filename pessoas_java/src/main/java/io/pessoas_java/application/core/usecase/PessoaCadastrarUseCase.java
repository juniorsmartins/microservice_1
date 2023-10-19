package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.in.PessoaCadastrarInputPort;
import io.pessoas_java.application.ports.out.PessoaCadastrarOutputPort;
import io.pessoas_java.config.exceptions.http_400.RequiredObjectIsNullException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.logging.Logger;

public class PessoaCadastrarUseCase implements PessoaCadastrarInputPort {

    private final Logger logger = Logger.getLogger(PessoaCadastrarUseCase.class.getName());

    private final PessoaCadastrarOutputPort pessoaCadastrarOutputPort;

    public PessoaCadastrarUseCase(PessoaCadastrarOutputPort pessoaCadastrarOutputPort) {
        this.pessoaCadastrarOutputPort = pessoaCadastrarOutputPort;
    }

    @Override
    public Pessoa cadastrar(Pessoa pessoa) {

        logger.info("UseCase - iniciado serviço de cadastrar uma pessoa.");

        if (ObjectUtils.isEmpty(pessoa)) throw new RequiredObjectIsNullException();

        var pessoaCadastrada = this.pessoaCadastrarOutputPort.salvar(pessoa);

        logger.info("UseCase - finalizado serviço de cadastrar uma pessoa.");

        return pessoaCadastrada;
    }
}

