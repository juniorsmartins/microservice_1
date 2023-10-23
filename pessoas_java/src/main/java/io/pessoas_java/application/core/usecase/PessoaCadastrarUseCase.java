package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.in.PessoaCadastrarInputPort;
import io.pessoas_java.application.ports.out.PessoaConsultarPorCpfOutputPort;
import io.pessoas_java.application.ports.out.PessoaSalvarOutputPort;
import io.pessoas_java.config.exceptions.http_400.CpfNaoUnicoException;
import io.pessoas_java.config.exceptions.http_400.RequiredObjectIsNullException;
import io.pessoas_java.config.exceptions.http_500.ErroInternoQualquerException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class PessoaCadastrarUseCase implements PessoaCadastrarInputPort {

    private final Logger logger = Logger.getLogger(PessoaCadastrarUseCase.class.getName());

    private final PessoaSalvarOutputPort pessoaSalvarOutputPort;

    private final PessoaConsultarPorCpfOutputPort pessoaConsultarPorCpfOutputPort;

    public PessoaCadastrarUseCase(PessoaSalvarOutputPort pessoaSalvarOutputPort,
                                  PessoaConsultarPorCpfOutputPort pessoaConsultarPorCpfOutputPort) {
        this.pessoaSalvarOutputPort = pessoaSalvarOutputPort;
        this.pessoaConsultarPorCpfOutputPort = pessoaConsultarPorCpfOutputPort;
    }

    @Override
    public Pessoa cadastrar(Pessoa pessoa) {

        logger.info("UseCase - iniciado serviço de cadastrar uma pessoa.");

        if (ObjectUtils.isEmpty(pessoa)) throw new RequiredObjectIsNullException();

        var pessoaCadastrada = Optional.of(pessoa)
            .map(this::verificarRegraCpfUnico)
            .map(this.pessoaSalvarOutputPort::salvar)
            .orElseThrow(NoSuchElementException::new);

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

