package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.filtro.EmailDtoFiltro;
import io.pessoas_java.application.core.domain.filtro.EmailFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailDtoFiltroMapper {

    EmailFiltro toEmailFiltro(EmailDtoFiltro emailDtoFiltro);
}

