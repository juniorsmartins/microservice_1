package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.request.UsuarioCadastrarDtoIn;
import io.pessoas_java.application.core.domain.value_object.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioCadastrarDtoInMapper {

    Usuario toUsuario(UsuarioCadastrarDtoIn usuarioCadastrarDtoIn);
}

