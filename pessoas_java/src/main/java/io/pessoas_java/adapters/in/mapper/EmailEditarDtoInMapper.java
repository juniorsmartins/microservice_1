package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.request.EmailEditarDtoIn;
import io.pessoas_java.application.core.domain.value_object.CorreioEletronico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailEditarDtoInMapper {

    CorreioEletronico toCorreioEletronico(EmailEditarDtoIn emailEditarDtoIn);
}

