package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.response.EmailCadastrarDtoOut;
import io.pessoas_java.application.core.domain.value_object.CorreioEletronico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailCadastrarDtoOutMapper {

    EmailCadastrarDtoOut toEmailCadastrarDtoOut(CorreioEletronico correioEletronico);
}

