package io.pessoas_java.adapters.out;

import io.pessoas_java.adapters.out.mapper.PessoaEntityMapper;
import io.pessoas_java.adapters.out.repository.PessoaRepository;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.ports.out.PessoaConsultarPorCpfOutputPort;
import io.pessoas_java.config.exceptions.http_400.CpfInvalidoException;
import io.pessoas_java.config.exceptions.http_404.PessoaNaoEncontradaPorCpfException;
import io.pessoas_java.config.exceptions.http_404.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PessoaConsultarPorCpfAdapter implements PessoaConsultarPorCpfOutputPort {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Override
    public Optional<Pessoa> consultarPorCpf(final String cpf) {

        return this.pessoaRepository.findByCpf(cpf)
                .map(this.pessoaEntityMapper::toPessoa);
    }
}

