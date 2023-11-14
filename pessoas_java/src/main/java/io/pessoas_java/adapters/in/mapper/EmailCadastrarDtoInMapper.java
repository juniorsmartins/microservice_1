package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.request.EmailCadastrarDtoIn;
import io.pessoas_java.application.core.domain.value_object.CorreioEletronico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailCadastrarDtoInMapper {

    CorreioEletronico toCorreioEletronico(EmailCadastrarDtoIn emailCadastrarDtoIn);
}

