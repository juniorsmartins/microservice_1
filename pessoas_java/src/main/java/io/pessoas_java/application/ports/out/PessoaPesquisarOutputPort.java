package io.pessoas_java.application.ports.out;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.PessoaFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaPesquisarOutputPort {

    Page<Pessoa> pesquisar(PessoaFiltro pessoaFiltro, Pageable paginacao);
}

