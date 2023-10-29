package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaConsultarPorCpfOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class PessoaConsultarPorCpfAdapter implements PessoaConsultarPorCpfOutputPort {

    private final Logger logger = Logger.getLogger(PessoaConsultarPorCpfAdapter.class.getName());

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Transactional(readOnly = true)
    @Override
    public Optional<Pessoa> consultarPorCpf(final String cpf) {

        logger.info("Adapter - iniciado processo de buscar uma pessoa no banco de dados.");

        var pessoaEncontrada = this.pessoaRepository.findByCpf(cpf)
                .map(this.pessoaEntityMapper::toPessoa);

        logger.info("Adapter - finalizado processo de buscar uma pessoa no banco de dados.");

        return pessoaEncontrada;
    }
}

