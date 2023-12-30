package io.micronoticias.adapter.in.mapper;

import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.adapter.in.dto.response.NoticiaPesquisarDtoOut;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaMapperDtoOut {

    NoticiaCriarDtoOut toNoticiaCriarDtoOut(NoticiaBusiness noticiaBusiness);

    NoticiaPesquisarDtoOut toNoticiaPesquisarDtoOut(NoticiaBusiness noticiaBusiness);
}

