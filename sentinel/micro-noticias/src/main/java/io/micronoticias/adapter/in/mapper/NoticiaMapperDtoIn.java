package io.micronoticias.adapter.in.mapper;

import io.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaMapperDtoIn {

    NoticiaBusiness toNoticiaBusiness(NoticiaCriarDtoIn noticiaCriarDtoIn);
}

