package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.adapters.out.spec.PessoaSpec;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.PessoaFiltro;
import io.pessoas_java.application.ports.out.PessoaPesquisarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToSearchException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PessoaPesquisarAdapter implements PessoaPesquisarOutputPort {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<Pessoa> pesquisar(final PessoaFiltro pessoaFiltro, final Pageable paginacao) {

        return Optional.of(pessoaFiltro)
            .map(parametros -> this.pessoaRepository.findAll(PessoaSpec.consultarDinamicamente(parametros), paginacao))
            .map(pagina -> pagina.map(this.pessoaEntityMapper::toPessoa))
            .orElseThrow(FailedToSearchException::new);
    }
}

