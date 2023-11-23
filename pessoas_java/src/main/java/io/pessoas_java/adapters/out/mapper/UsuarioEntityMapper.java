package io.pessoas_java.adapters.out.mapper;

import io.pessoas_java.adapters.out.entity.UsuarioEntity;
import io.pessoas_java.application.core.domain.value_object.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntity toUsuarioEntity(Usuario usuario);

    Usuario toUsuario(UsuarioEntity usuarioEntity);
}

