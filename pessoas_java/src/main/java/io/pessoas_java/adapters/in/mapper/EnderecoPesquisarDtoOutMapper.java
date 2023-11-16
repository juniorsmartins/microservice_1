package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.response.EnderecoPesquisarDtoOut;
import io.pessoas_java.application.core.domain.value_object.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoPesquisarDtoOutMapper {

    EnderecoPesquisarDtoOut toEnderecoPesquisarDtoOut(Endereco endereco);
}

