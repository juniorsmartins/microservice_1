package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.filtro.TelefoneDtoFiltro;
import io.pessoas_java.application.core.domain.filtro.TelefoneFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelefoneDtoFiltroMapper {

    TelefoneFiltro toTelefoneFiltro(TelefoneDtoFiltro telefoneDtoFiltro);
}

