package io.micronoticias.adapter.in.mapper;

import io.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaMapperDto {

    NoticiaBusiness toNoticiaBusiness(NoticiaCriarDtoIn noticiaCriarDtoIn);

    NoticiaCriarDtoOut toNoticiaCriarDtoOut(NoticiaBusiness noticiaBusiness);
}

