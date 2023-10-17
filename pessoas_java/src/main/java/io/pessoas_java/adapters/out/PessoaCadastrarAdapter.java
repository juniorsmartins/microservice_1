package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaCadastrarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToSaveException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PessoaCadastrarAdapter implements PessoaCadastrarOutputPort {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Transactional
    @Override
    public Pessoa salvar(Pessoa pessoa) {

        return Optional.of(pessoa)
            .map(this.pessoaEntityMapper::toPessoaEntity)
            .map(this.pessoaRepository::save)
            .map(this.pessoaEntityMapper::toPessoa)
            .orElseThrow(FailedToSaveException::new);
    }
}

