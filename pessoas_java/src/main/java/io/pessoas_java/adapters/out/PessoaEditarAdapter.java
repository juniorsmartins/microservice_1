package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import io.pessoas_java.adapters.out.mapper.EmailEntityMapper;
import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.mapper.TelefoneEntityMapper;
import io.pessoas_java.adapters.out.repository.EmailRepository;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.adapters.out.repository.TelefoneRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaEditarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToEditException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class PessoaEditarAdapter implements PessoaEditarOutputPort {

    private final Logger logger = Logger.getLogger(PessoaEditarAdapter.class.getName());

    private final PessoaEntityMapper pessoaEntityMapper;

    private final TelefoneEntityMapper telefoneEntityMapper;

    private final EmailEntityMapper emailEntityMapper;

    private final PessoaRepository pessoaRepository;

    private final TelefoneRepository telefoneRepository;

    private final EmailRepository emailRepository;

    @Transactional
    @Override
    public Pessoa editar(Pessoa pessoa) {

        logger.info("Adapter - iniciado processo de editar uma pessoa.");

        var pessoaEditada = this.pessoaRepository.findByChave(pessoa.getChave())
            .map(people -> {
                BeanUtils.copyProperties(pessoa, people, "id", "chave");

                people = this.atualizarTelefones(pessoa, people);
                people = this.atualizarEmails(pessoa, people);

                BeanUtils.copyProperties(pessoa.getEndereco(), people.getEndereco());
                return people;
            })
            .map(this.pessoaEntityMapper::toPessoa)
            .orElseThrow(FailedToEditException::new);

        logger.info("Adapter - finalizado processo de editar uma pessoa.");

        return pessoaEditada;
    }

    private PessoaEntity atualizarTelefones(Pessoa pessoa, PessoaEntity people) {
        people.getTelefones().clear();

        pessoa.getTelefones().forEach(fone -> {
            var foneEntity = this.telefoneEntityMapper.toTelefoneEntity(fone);
            foneEntity = this.telefoneRepository.save(foneEntity);
            people.getTelefones().add(foneEntity);
        });

        return people;
    }

    private PessoaEntity atualizarEmails(Pessoa pessoa, PessoaEntity people) {
        people.getEmails().clear();

        pessoa.getEmails().forEach(mail -> {
            var mailEntity = this.emailEntityMapper.toEmailEntity(mail);
            mailEntity = this.emailRepository.save(mailEntity);
            people.getEmails().add(mailEntity);
        });

        return people;
    }
}

