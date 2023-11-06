package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.regras.RegrasCadastrar;
import io.pessoas_java.application.core.domain.utils.Util;
import io.pessoas_java.application.ports.in.PessoaCadastrarInputPort;
import io.pessoas_java.application.ports.out.PessoaSalvarOutputPort;
import io.pessoas_java.config.exceptions.http_400.RequiredObjectIsNullException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class PessoaCadastrarUseCase implements PessoaCadastrarInputPort {

    private final Logger logger = Logger.getLogger(PessoaCadastrarUseCase.class.getName());

    private final PessoaSalvarOutputPort pessoaSalvarOutputPort;

    private final List<RegrasCadastrar> listaRegrasCadastrar;

    private final Util util;

    public PessoaCadastrarUseCase(PessoaSalvarOutputPort pessoaSalvarOutputPort,
                                  RegrasCadastrar regrasCpfUnico,
                                  Util util) {
        this.pessoaSalvarOutputPort = pessoaSalvarOutputPort;
        this.listaRegrasCadastrar = List.of(regrasCpfUnico);
        this.util = util;
    }

    @Override
    public Pessoa cadastrar(Pessoa pessoa) {

        logger.info("UseCase - iniciado serviço de cadastrar uma pessoa.");

        if (ObjectUtils.isEmpty(pessoa)) throw new RequiredObjectIsNullException();

        var pessoaCadastrada = Optional.of(pessoa)
            .map(people -> {
                this.listaRegrasCadastrar.forEach(regra -> regra.executar(people));
                return people;
            })
            .map(this::capitalizarNomeCompleto)
            .map(this.pessoaSalvarOutputPort::salvar)
            .orElseThrow(NoSuchElementException::new);

        logger.info("UseCase - finalizado serviço de cadastrar uma pessoa.");

        return pessoaCadastrada;
    }

    private Pessoa capitalizarNomeCompleto(Pessoa pessoa) {
        var nomeCapitalizado = this.util.capitalizar(pessoa.getNome());
        pessoa.setNome(nomeCapitalizado);

        var sobrenomeCapitalizado = this.util.capitalizar(pessoa.getSobrenome());
        pessoa.setSobrenome(sobrenomeCapitalizado);

        return pessoa;
    }
}

