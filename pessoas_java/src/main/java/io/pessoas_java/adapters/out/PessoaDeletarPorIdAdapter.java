package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.ports.out.PessoaDeletarPorIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class PessoaDeletarPorIdAdapter implements PessoaDeletarPorIdOutputPort {

    private final Logger logger = Logger.getLogger(PessoaDeletarPorIdAdapter.class.getName());

    private final PessoaRepository pessoaRepository;

    private final PessoaEntityMapper pessoaEntityMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void deletarPorId(final Long id) {

        logger.info("Adapter - iniciado processo de deletar uma pessoa por chave.");

        this.pessoaRepository.deleteById(id);

        logger.info("Adapter - finalizado processo de deletar uma pessoa por chave.");
    }
}

