package io.micro_texto.adapters.in.mapper;

import io.micro_texto.adapters.in.dto.request.NoticiaCriarDtoIn;
import io.micro_texto.adapters.in.dto.response.NoticiaCriarDtoOut;
import io.micro_texto.application.core.domain.NoticiaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaDtoMapper {

    NoticiaBusiness toNoticiaBusiness(NoticiaCriarDtoIn noticiaCriarDtoIn);

    NoticiaCriarDtoOut toNoticiaCriarDtoOut(NoticiaBusiness noticiaBusiness);
}

