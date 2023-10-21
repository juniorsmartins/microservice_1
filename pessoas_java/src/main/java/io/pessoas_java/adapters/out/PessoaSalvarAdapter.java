package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaSalvarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToSaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class PessoaSalvarAdapter implements PessoaSalvarOutputPort {

    private final Logger logger = Logger.getLogger(PessoaSalvarAdapter.class.getName());

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Transactional
    @Override
    public Pessoa salvar(Pessoa pessoa) {

        logger.info("Adapter - iniciado processo de salvar uma pessoa.");

        var pessoaSalva = Optional.of(pessoa)
            .map(this.pessoaEntityMapper::toPessoaEntity)
            .map(this.pessoaRepository::save)
            .map(this.pessoaEntityMapper::toPessoa)
            .orElseThrow(FailedToSaveException::new);

        logger.info("Adapter - finalizado processo de salvar uma pessoa.");

        return pessoaSalva;
    }
}

