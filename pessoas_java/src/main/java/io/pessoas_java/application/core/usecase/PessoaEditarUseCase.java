package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.regras.RegrasEditar;
import io.pessoas_java.application.core.domain.utils.Util;
import io.pessoas_java.application.ports.in.PessoaEditarInputPort;
import io.pessoas_java.application.ports.out.PessoaEditarOutputPort;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class PessoaEditarUseCase implements PessoaEditarInputPort {

    private final Logger logger = Logger.getLogger(PessoaEditarUseCase.class.getName());

    private final PessoaEditarOutputPort pessoaEditarOutputPort;

    private final List<RegrasEditar> listaRegrasEditar;

    private final Util util;

    public PessoaEditarUseCase(PessoaEditarOutputPort pessoaEditarOutputPort,
                               RegrasEditar regrasCpfUnico,
                               Util util) {
        this.pessoaEditarOutputPort = pessoaEditarOutputPort;
        this.listaRegrasEditar = List.of(regrasCpfUnico);
        this.util = util;
    }

    @Override
    public Pessoa editar(Pessoa pessoa) {

        logger.info("UseCase - iniciado serviço de editar uma pessoa.");

        var pessoaEditada = Optional.of(pessoa)
            .map(people -> {
                this.listaRegrasEditar.forEach(regra -> regra.executar(people));
                return people;
            })
            .map(this::capitalizarNomeCompleto)
            .map(this.pessoaEditarOutputPort::editar)
            .orElseThrow(NoSuchElementException::new);

        logger.info("UseCase - finalizado serviço de editar uma pessoa.");

        return pessoaEditada;
    }

    private Pessoa capitalizarNomeCompleto(Pessoa pessoa) {
        var nomeCapitalizado = this.util.capitalizar(pessoa.getNome());
        pessoa.setNome(nomeCapitalizado);

        var sobrenomeCapitalizado = this.util.capitalizar(pessoa.getSobrenome());
        pessoa.setSobrenome(sobrenomeCapitalizado);

        return pessoa;
    }
}

