package io.pessoas_java.adapters.out.mapper;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import io.pessoas_java.application.core.domain.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PessoaEntityMapper {

    @Mapping(source = "usuario.username", target = "usuario.username")
    @Mapping(source = "usuario.password", target = "usuario.password")
    PessoaEntity toPessoaEntity(Pessoa pessoa);

    @Mapping(source = "usuario.username", target = "usuario.username")
    Pessoa toPessoa(PessoaEntity pessoaEntity);
}

