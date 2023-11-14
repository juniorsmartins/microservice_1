package io.pessoas_java.application.core.usecase;

import io.pessoas_java.application.ports.in.PessoaConsultarPorChaveInputPort;
import io.pessoas_java.application.ports.in.PessoaDeletarPorChaveInputPort;
import io.pessoas_java.application.ports.out.PessoaDeletarPorIdOutputPort;
import io.pessoas_java.config.exceptions.http_404.PessoaNaoEncontradaPorChaveException;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class PessoaDeletarPorChaveUseCase implements PessoaDeletarPorChaveInputPort {

    private final Logger logger = Logger.getLogger(PessoaDeletarPorChaveUseCase.class.getName());

    private final PessoaConsultarPorChaveInputPort pessoaConsultarPorChaveInputPort;

    private final PessoaDeletarPorIdOutputPort pessoaDeletarPorIdOutputPort;

    public PessoaDeletarPorChaveUseCase(PessoaConsultarPorChaveInputPort pessoaConsultarPorChaveInputPort,
                                        PessoaDeletarPorIdOutputPort pessoaDeletarPorIdOutputPort) {
        this.pessoaConsultarPorChaveInputPort = pessoaConsultarPorChaveInputPort;
        this.pessoaDeletarPorIdOutputPort = pessoaDeletarPorIdOutputPort;
    }

    @Override
    public void deletar(final UUID chave) {

        logger.info("UseCase - iniciado serviço de deletar uma pessoa por chave.");

        Optional.ofNullable(chave)
            .ifPresentOrElse(
                key -> {
                    var pessoa = this.pessoaConsultarPorChaveInputPort.consultarPorChave(key);
                    this.pessoaDeletarPorIdOutputPort.deletarPorId(pessoa.getId());
                },
                () -> {throw new PessoaNaoEncontradaPorChaveException(chave);}
            );

        logger.info("UseCase - finalizado serviço de deletar uma pessoa por chave.");
    }
}

