package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.request.EnderecoCadastrarDtoIn;
import io.pessoas_java.application.core.domain.value_object.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoCadastrarDtoInMapper {

    Endereco toEndereco(EnderecoCadastrarDtoIn enderecoCadastrarDtoIn);
}

