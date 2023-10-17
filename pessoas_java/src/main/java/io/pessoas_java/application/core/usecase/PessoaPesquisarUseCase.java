package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.PessoaFiltro;
import io.pessoas_java.application.ports.in.PessoaPesquisarInputPort;
import io.pessoas_java.application.ports.out.PessoaPesquisarOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PessoaPesquisarUseCase implements PessoaPesquisarInputPort {

    private final PessoaPesquisarOutputPort pessoaPesquisarOutputPort;

    public PessoaPesquisarUseCase(PessoaPesquisarOutputPort pessoaPesquisarOutputPort) {
        this.pessoaPesquisarOutputPort = pessoaPesquisarOutputPort;
    }

    @Override
    public Page<Pessoa> pesquisar(final PessoaFiltro pessoaFiltro, final Pageable paginacao) {

        return this.pessoaPesquisarOutputPort.pesquisar(pessoaFiltro, paginacao);
    }
}

