package io.pessoas_java.adapters.out.mapper;

import io.pessoas_java.adapters.out.entity.EnderecoEntity;
import io.pessoas_java.application.core.domain.value_object.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoEntityMapper {

    EnderecoEntity toEnderecoEntity(Endereco endereco);

    Endereco toEndereco(EnderecoEntity enderecoEntity);
}

