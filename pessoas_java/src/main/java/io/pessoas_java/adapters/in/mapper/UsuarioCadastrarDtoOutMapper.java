package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.response.UsuarioCadastrarDtoOut;
import io.pessoas_java.application.core.domain.value_object.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioCadastrarDtoOutMapper {

    UsuarioCadastrarDtoOut toUsuarioCadastrarDtoOut(Usuario usuario);
}

