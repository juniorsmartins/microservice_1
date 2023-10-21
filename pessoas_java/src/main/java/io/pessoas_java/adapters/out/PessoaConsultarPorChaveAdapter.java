package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaConsultarPorChaveOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class PessoaConsultarPorChaveAdapter implements PessoaConsultarPorChaveOutputPort {

    private final Logger logger = Logger.getLogger(PessoaConsultarPorChaveAdapter.class.getName());

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Transactional(readOnly = true)
    @Override
    public Optional<Pessoa> consultarPorChave(final UUID chave) {

        logger.info("Adapter - iniciado processo de buscar uma pessoa no banco de dados.");

        var pessoaEncontrada = this.pessoaRepository.findByChave(chave)
            .map(this.pessoaEntityMapper::toPessoa);

        logger.info("Adapter - finalizado processo de buscar uma pessoa no banco de dados.");

        return pessoaEncontrada;
    }
}

