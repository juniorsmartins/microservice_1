package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.adapters.out.repository.UsuarioRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaSalvarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToSaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class PessoaSalvarAdapter implements PessoaSalvarOutputPort {

    private final Logger logger = Logger.getLogger(PessoaSalvarAdapter.class.getName());

    private final PessoaRepository pessoaRepository;

    private final UsuarioRepository usuarioRepository;

    private final PessoaEntityMapper pessoaEntityMapper;

    @Transactional
    @Override
    public Pessoa salvar(Pessoa pessoa) {

        logger.info("Adapter - iniciado processo de salvar uma Pessoa.");

        var pessoaSalva = Optional.of(pessoa)
            .map(this.pessoaEntityMapper::toPessoaEntity)
            .map(this.pessoaRepository::save)
            .map(this::salvarUsuario)
            .map(this.pessoaEntityMapper::toPessoa)
            .orElseThrow(FailedToSaveException::new);

        logger.info("Adapter - finalizado processo de salvar uma Pessoa.");

        return pessoaSalva;
    }

    private PessoaEntity salvarUsuario(PessoaEntity pessoaEntity) {
        var user = pessoaEntity.getUsuario();
        user.setPessoa(pessoaEntity);
        this.usuarioRepository.save(user);
        return pessoaEntity;
    }
}

