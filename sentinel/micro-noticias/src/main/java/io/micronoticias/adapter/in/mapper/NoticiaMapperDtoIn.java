package io.micronoticias.adapter.in.mapper;

import io.micronoticias.adapter.in.dto.filtro.NoticiaFiltroDtoIn;
import io.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.core.domain.filtro.NoticiaFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaMapperDtoIn {

    NoticiaBusiness toNoticiaBusiness(NoticiaCriarDtoIn noticiaCriarDtoIn);

    NoticiaFiltro toNoticiaFiltro(NoticiaFiltroDtoIn noticiaFiltroDtoIn);
}

