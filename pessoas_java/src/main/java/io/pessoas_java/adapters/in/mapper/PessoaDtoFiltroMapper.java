package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.filtro.PessoaDtoFiltro;
import io.pessoas_java.application.core.domain.PessoaFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaDtoFiltroMapper {

    PessoaFiltro toPessoaFiltro(PessoaDtoFiltro pessoaDtoFiltro);
}

