package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.request.PessoaDtoIn;
import io.pessoas_java.application.core.domain.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaDtoInMapper {

    Pessoa toPessoa(PessoaDtoIn pessoaDtoIn);
}

