package io.micro_noticia.adapters.out.mapper;

import io.micro_noticia.adapters.out.entity.NoticiaEntity;
import io.micro_noticia.application.core.domain.NoticiaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaEntityMapper {

    NoticiaEntity toNoticiaEntity(NoticiaBusiness noticiaBusiness);

    NoticiaBusiness toNoticiaBusiness(NoticiaEntity noticiaEntity);
}

