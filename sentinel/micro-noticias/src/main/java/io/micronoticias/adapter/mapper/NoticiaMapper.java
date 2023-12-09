package io.micronoticias.adapter.mapper;

import io.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.adapter.out.entity.NoticiaEntity;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaMapper {

    NoticiaBusiness toNoticiaBusiness(NoticiaCriarDtoIn noticiaCriarDtoIn);

    NoticiaCriarDtoOut fromNoticiaBusiness(NoticiaBusiness noticiaBusiness);

    NoticiaEntity toNoticiaEntity(NoticiaBusiness noticiaBusiness);

    NoticiaBusiness fromNoticiaEntity(NoticiaEntity noticiaEntity);
}

