package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.response.PessoaDtoOut;
import io.pessoas_java.application.core.domain.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PessoaDtoOutMapper {

    PessoaDtoOut toPessoaDtoOut(Pessoa pessoa);
}

