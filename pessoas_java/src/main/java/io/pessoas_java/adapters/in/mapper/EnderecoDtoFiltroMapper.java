package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.filtro.EnderecoDtoFiltro;
import io.pessoas_java.application.core.domain.filtro.EnderecoFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoDtoFiltroMapper {

    EnderecoFiltro toEnderecoFiltro(EnderecoDtoFiltro enderecoDtoFiltro);
}

