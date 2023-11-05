package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaEditarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToEditException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Component
public class PessoaEditarAdapter implements PessoaEditarOutputPort {

    private final Logger logger = Logger.getLogger(PessoaEditarAdapter.class.getName());

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    @Override
    public Pessoa editar(Pessoa pessoa) {

        logger.info("Adapter - iniciado processo de editar uma pessoa.");

        var pessoaEditada = this.pessoaRepository.findByChave(pessoa.getChave())
            .map(people -> {
                BeanUtils.copyProperties(pessoa, people, "id", "chave");
                BeanUtils.copyProperties(pessoa.getUsuario(), people.getUsuario(), "id");
                return people;
            })
            .map(this.pessoaEntityMapper::toPessoa)
            .orElseThrow(FailedToEditException::new);

        logger.info("Adapter - finalizado processo de editar uma pessoa.");

        return pessoaEditada;
    }
}

