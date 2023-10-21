package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.in.PessoaCadastrarInputPort;
import io.pessoas_java.application.ports.out.PessoaCadastrarOutputPort;
import io.pessoas_java.application.ports.out.PessoaConsultarPorCpfOutputPort;
import io.pessoas_java.config.exceptions.http_400.CpfNaoUnicoException;
import io.pessoas_java.config.exceptions.http_400.RequiredObjectIsNullException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;
import java.util.logging.Logger;

public class PessoaCadastrarUseCase implements PessoaCadastrarInputPort {

    private final Logger logger = Logger.getLogger(PessoaCadastrarUseCase.class.getName());

    private final PessoaCadastrarOutputPort pessoaCadastrarOutputPort;

    private final PessoaConsultarPorCpfOutputPort pessoaConsultarPorCpfOutputPort;

    public PessoaCadastrarUseCase(PessoaCadastrarOutputPort pessoaCadastrarOutputPort,
                                  PessoaConsultarPorCpfOutputPort pessoaConsultarPorCpfOutputPort) {
        this.pessoaCadastrarOutputPort = pessoaCadastrarOutputPort;
        this.pessoaConsultarPorCpfOutputPort = pessoaConsultarPorCpfOutputPort;
    }

    @Override
    public Pessoa cadastrar(Pessoa pessoa) {

        logger.info("UseCase - iniciado serviço de cadastrar uma pessoa.");

        if (ObjectUtils.isEmpty(pessoa)) throw new RequiredObjectIsNullException();

        var pessoaCadastrada = Optional.of(pessoa)
            .map(this::verificarRegraCpfUnico)
            .map(this.pessoaCadastrarOutputPort::salvar)
            .orElseThrow();

        logger.info("UseCase - finalizado serviço de cadastrar uma pessoa.");

        return pessoaCadastrada;
    }

    private Pessoa verificarRegraCpfUnico(Pessoa pessoa) {

        logger.info("Verificação de regra de CPF único.");

        var pessoaPersistida = this.pessoaConsultarPorCpfOutputPort.consultarPorCpf(pessoa.getCpf());
        if (pessoaPersistida.isPresent() &&
                (!pessoa.getChave().equals(pessoaPersistida.get().getChave()))) {
            throw new CpfNaoUnicoException(pessoa.getCpf());
        }

        logger.info("CPF verificado como único.");

        return pessoa;
    }
}

