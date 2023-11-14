package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.EnderecoEntityMapper;
import io.pessoas_java.adapters.out.repository.EnderecoRepository;
import io.pessoas_java.application.core.domain.value_object.Endereco;
import io.pessoas_java.application.ports.out.EnderecoSalvarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToDeleteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class EnderecoSalvarAdapter implements EnderecoSalvarOutputPort {

    private final Logger logger = Logger.getLogger(EnderecoSalvarAdapter.class.getName());

    private final EnderecoRepository enderecoRepository;

    private final EnderecoEntityMapper enderecoEntityMapper;

    @Transactional
    @Override
    public Endereco salvar(Endereco endereco) {

        this.logger.info("Adapter - iniciado processo de salvar um Endereço.");

        var enderecoSalvo = Optional.of(endereco)
            .map(this.enderecoEntityMapper::toEnderecoEntity)
            .map(this.enderecoRepository::save)
            .map(this.enderecoEntityMapper::toEndereco)
            .orElseThrow(FailedToDeleteException::new);

        this.logger.info("Adapter - finalizado processo de salvar um Endereço.");

        return enderecoSalvo;
    }
}

