package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaDeletarOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class PessoaDeletarAdapter implements PessoaDeletarOutputPort {

    private final Logger logger = Logger.getLogger(PessoaDeletarAdapter.class.getName());

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void deletar(final Pessoa pessoa) {

        logger.info("Adapter - iniciado processo de deletar uma pessoa por chave.");

        Optional.of(pessoa)
            .map(this.pessoaEntityMapper::toPessoaEntity)
            .map(entidade -> {
                this.pessoaRepository.delete(entidade);
                return true;
            });

        logger.info("Adapter - finalizado processo de deletar uma pessoa por chave.");
    }
}

