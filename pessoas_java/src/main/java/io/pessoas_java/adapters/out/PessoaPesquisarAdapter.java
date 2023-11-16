package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.adapters.out.spec.PessoaSpec;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.filtro.PessoaFiltro;
import io.pessoas_java.application.ports.out.PessoaPesquisarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToSearchException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class PessoaPesquisarAdapter implements PessoaPesquisarOutputPort {

    private final Logger logger = Logger.getLogger(PessoaPesquisarAdapter.class.getName());

    private final PessoaRepository pessoaRepository;

    private final PessoaEntityMapper pessoaEntityMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<Pessoa> pesquisar(final PessoaFiltro pessoaFiltro, final Pageable paginacao) {

        logger.info("Adapter - iniciado processo de pesquisar pessoas.");

        var paginaPessoas = Optional.of(pessoaFiltro)
            .map(parametros -> this.pessoaRepository.findAll(PessoaSpec.consultarDinamicamente(parametros), paginacao))
            .map(pagina -> pagina.map(this.pessoaEntityMapper::toPessoa))
            .orElseThrow(FailedToSearchException::new);

        logger.info("Adapter - finalizado processo de pesquisar pessoas.");

        return paginaPessoas;
    }
}

