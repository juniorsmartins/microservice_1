package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.ports.in.PessoaDeletarPorChaveInputPort;
import io.pessoas_java.application.ports.out.PessoaConsultarPorChaveOutputPort;
import io.pessoas_java.application.ports.out.PessoaDeletarPorIdOutputPort;
import io.pessoas_java.config.exceptions.http_404.PessoaNaoEncontradaPorChaveException;

import java.util.UUID;
import java.util.logging.Logger;

public class PessoaDeletarPorChaveUseCase implements PessoaDeletarPorChaveInputPort {

    private final Logger logger = Logger.getLogger(PessoaDeletarPorChaveUseCase.class.getName());

    private final PessoaConsultarPorChaveOutputPort pessoaConsultarPorChaveOutputPort;

    private final PessoaDeletarPorIdOutputPort pessoaDeletarPorIdOutputPort;

    public PessoaDeletarPorChaveUseCase(PessoaConsultarPorChaveOutputPort pessoaConsultarPorChaveOutputPort,
                                        PessoaDeletarPorIdOutputPort pessoaDeletarPorIdOutputPort) {
        this.pessoaConsultarPorChaveOutputPort = pessoaConsultarPorChaveOutputPort;
        this.pessoaDeletarPorIdOutputPort = pessoaDeletarPorIdOutputPort;
    }

    @Override
    public void deletar(final UUID chave) {

        logger.info("UseCase - iniciado serviço de deletar uma pessoa por chave.");

        this.pessoaConsultarPorChaveOutputPort.consultarPorChave(chave)
            .map(pessoa -> {
                this.pessoaDeletarPorIdOutputPort.deletarPorId(pessoa.getId());
                return true;
            })
            .orElseThrow(() -> new PessoaNaoEncontradaPorChaveException(chave));

        logger.info("UseCase - finalizado serviço de deletar uma pessoa por chave.");
    }
}

