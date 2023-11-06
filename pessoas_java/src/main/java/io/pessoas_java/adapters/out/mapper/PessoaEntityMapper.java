package io.pessoas_java.adapters.out.mapper;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import io.pessoas_java.application.core.domain.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaEntityMapper {

    PessoaEntity toPessoaEntity(Pessoa pessoa);

    Pessoa toPessoa(PessoaEntity pessoaEntity);
}

