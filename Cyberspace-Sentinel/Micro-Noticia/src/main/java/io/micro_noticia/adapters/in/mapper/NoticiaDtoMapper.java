package io.micro_noticia.adapters.in.mapper;

import io.micro_noticia.adapters.in.dto.request.NoticiaCriarDtoIn;
import io.micro_noticia.adapters.in.dto.response.NoticiaCriarDtoOut;
import io.micro_noticia.application.core.domain.NoticiaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaDtoMapper {

    NoticiaBusiness toNoticiaBusiness(NoticiaCriarDtoIn noticiaCriarDtoIn);

    NoticiaCriarDtoOut toNoticiaCriarDtoOut(NoticiaBusiness noticiaBusiness);
}

