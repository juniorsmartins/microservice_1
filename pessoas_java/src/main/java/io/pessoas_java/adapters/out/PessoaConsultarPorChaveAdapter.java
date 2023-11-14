package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaConsultarPorChaveOutputPort;
import io.pessoas_java.config.exceptions.http_404.PessoaNaoEncontradaPorChaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class PessoaConsultarPorChaveAdapter implements PessoaConsultarPorChaveOutputPort {

    private final Logger logger = Logger.getLogger(PessoaConsultarPorChaveAdapter.class.getName());

    private final PessoaRepository pessoaRepository;

    private final PessoaEntityMapper pessoaEntityMapper;

    @Transactional(readOnly = true)
    @Override
    public Pessoa consultarPorChave(final UUID chave) {

        logger.info("Adapter - iniciado processo de buscar uma pessoa no banco de dados.");

        var pessoaEncontrada = this.pessoaRepository.findByChave(chave)
            .map(this.pessoaEntityMapper::toPessoa)
            .orElseThrow(() -> new PessoaNaoEncontradaPorChaveException(chave));

        logger.info("Adapter - finalizado processo de buscar uma pessoa no banco de dados.");

        return pessoaEncontrada;
    }
}

