package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.ports.out.PessoaDeletarPorChaveOutputPort;
import io.pessoas_java.config.exceptions.http_400.RequiredObjectIsNullException;
import io.pessoas_java.config.exceptions.http_404.PessoaNaoEncontradaPorChaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class PessoaDeletarPorChaveAdapter implements PessoaDeletarPorChaveOutputPort {

    private final Logger logger = Logger.getLogger(PessoaDeletarPorChaveAdapter.class.getName());

    private final PessoaRepository pessoaRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void deletarPorChave(final UUID chave) {

        logger.info("Adapter - iniciado processo de deletar uma pessoa por chave.");

        Optional.ofNullable(chave)
            .ifPresentOrElse(
                key -> {
                    var existe = this.pessoaRepository.existsByChave(key);
                    if (!existe) {throw new PessoaNaoEncontradaPorChaveException(key);}
                    this.pessoaRepository.deleteByChave(key);
                },
                () -> {throw new RequiredObjectIsNullException();}
            );

        logger.info("Adapter - finalizado processo de deletar uma pessoa por chave.");
    }
}

