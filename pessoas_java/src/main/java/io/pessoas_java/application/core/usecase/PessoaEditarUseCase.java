package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.regras.RegrasEditar;
import io.pessoas_java.application.ports.in.PessoaEditarInputPort;
import io.pessoas_java.application.ports.out.PessoaEditarOutputPort;
import io.pessoas_java.application.ports.out.PessoaSalvarOutputPort;
import io.pessoas_java.config.exceptions.http_500.ErroInternoQualquerException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PessoaEditarUseCase implements PessoaEditarInputPort {

    private final Logger logger = Logger.getLogger(PessoaEditarUseCase.class.getName());

    private final PessoaEditarOutputPort pessoaEditarOutputPort;

    private final List<RegrasEditar> listaRegrasEditar;

    public PessoaEditarUseCase(PessoaEditarOutputPort pessoaEditarOutputPort,
                               RegrasEditar regrasCpfUnico) {
        this.pessoaEditarOutputPort = pessoaEditarOutputPort;
        this.listaRegrasEditar = List.of(regrasCpfUnico);
    }

    @Override
    public Pessoa editar(Pessoa pessoa) {

        logger.info("UseCase - iniciado serviço de editar uma pessoa.");

        var pessoaEditada = Optional.of(pessoa)
            .map(people -> {
                this.listaRegrasEditar.forEach(regra -> regra.executar(people));
                return people;
            })
            .map(this.pessoaEditarOutputPort::editar)
            .orElseThrow(ErroInternoQualquerException::new);

        logger.info("UseCase - finalizado serviço de editar uma pessoa.");

        return pessoaEditada;
    }
}

