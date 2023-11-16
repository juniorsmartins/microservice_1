package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.PessoaFiltro;
import io.pessoas_java.application.ports.in.PessoaPesquisarInputPort;
import io.pessoas_java.application.ports.out.PessoaPesquisarOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.logging.Logger;

public class PessoaPesquisarUseCase implements PessoaPesquisarInputPort {

    private final Logger logger = Logger.getLogger(PessoaPesquisarUseCase.class.getName());

    private final PessoaPesquisarOutputPort pessoaPesquisarOutputPort;

    public PessoaPesquisarUseCase(PessoaPesquisarOutputPort pessoaPesquisarOutputPort) {
        this.pessoaPesquisarOutputPort = pessoaPesquisarOutputPort;
    }

    @Override
    public Page<Pessoa> pesquisar(final PessoaFiltro pessoaFiltro, final Pageable paginacao) {

        logger.info("UseCase - iniciado serviço de pesquisar Pessoas.");

        var pagina = this.pessoaPesquisarOutputPort.pesquisar(pessoaFiltro, paginacao);

        logger.info("UseCase - finalizado serviço de pesquisar Pessoas.");

        return pagina;
    }
}

