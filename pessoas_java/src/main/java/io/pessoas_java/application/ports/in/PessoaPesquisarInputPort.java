package io.pessoas_java.application.ports.in;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.PessoaFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaPesquisarInputPort {

    Page<Pessoa> pesquisar(PessoaFiltro pessoaFiltro, Pageable paginacao);
}

