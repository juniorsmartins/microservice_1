package io.pessoas_java.adapters.out.mapper;

import io.pessoas_java.adapters.out.entity.EmailEntity;
import io.pessoas_java.application.core.domain.value_object.CorreioEletronico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailEntityMapper {

    EmailEntity toEmailEntity(CorreioEletronico correioEletronico);

    CorreioEletronico toCorreioEletronico(EmailEntity emailEntity);
}

