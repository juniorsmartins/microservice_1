package io.micro_texto.adapters.out.mapper;

import io.micro_texto.adapters.out.entity.NoticiaEntity;
import io.micro_texto.application.core.domain.NoticiaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaEntityMapper {

    NoticiaEntity toNoticiaEntity(NoticiaBusiness noticiaBusiness);

    NoticiaBusiness toNoticiaBusiness(NoticiaEntity noticiaEntity);
}

