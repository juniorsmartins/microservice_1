package io.pessoas_java.adapters.out.mapper;

import io.pessoas_java.adapters.out.entity.TelefoneEntity;
import io.pessoas_java.application.core.domain.value_object.Telefone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelefoneEntityMapper {

    TelefoneEntity toTelefoneEntity(Telefone telefone);

    Telefone toTelefone(TelefoneEntity telefoneEntity);
}

