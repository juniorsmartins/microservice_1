package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.ports.out.PessoaDeletarPorIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Component
public class PessoaDeletarPorIdAdapter implements PessoaDeletarPorIdOutputPort {

    private final Logger logger = Logger.getLogger(PessoaDeletarPorIdAdapter.class.getName());

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void deletarPorId(final Long id) {

        logger.info("Adapter - iniciado processo de deletar uma pessoa por chave.");
        this.pessoaRepository.deleteById(id);
        logger.info("Adapter - finalizado processo de deletar uma pessoa por chave.");
    }
}

