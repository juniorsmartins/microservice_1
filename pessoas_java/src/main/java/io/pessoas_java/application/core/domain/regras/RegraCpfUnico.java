package io.pessoas_java.application.core.domain.regras;

import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaConsultarPorCpfOutputPort;
import io.pessoas_java.config.exceptions.http_409.RegraCpfNaoUnicoException;

public class RegraCpfUnico implements RegrasEditar, RegrasCadastrar {

    private final PessoaConsultarPorCpfOutputPort pessoaConsultarPorCpfOutputPort;

    public RegraCpfUnico(PessoaConsultarPorCpfOutputPort pessoaConsultarPorCpfOutputPort) {
        this.pessoaConsultarPorCpfOutputPort = pessoaConsultarPorCpfOutputPort;
    }

    @Override
    public void executar(Pessoa pessoa) {

        this.pessoaConsultarPorCpfOutputPort.consultarPorCpf(pessoa.getCpf())
            .filter(pessoaPersistida -> !pessoa.getChave().equals(pessoaPersistida.getChave()))
            .ifPresent(p -> {
                throw new RegraCpfNaoUnicoException(pessoa.getCpf());
            });
    }
}

