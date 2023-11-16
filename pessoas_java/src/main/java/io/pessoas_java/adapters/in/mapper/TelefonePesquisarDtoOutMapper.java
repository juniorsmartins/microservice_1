package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.response.TelefonePesquisarDtoOut;
import io.pessoas_java.application.core.domain.value_object.Telefone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelefonePesquisarDtoOutMapper {

    TelefonePesquisarDtoOut toTelefonePesquisarDtoOut(Telefone telefone);
}

