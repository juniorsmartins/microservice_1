package io.micronoticias.adapter.in.dto.filtro;

import io.micronoticias.application.core.domain.NoticiaBusiness;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record NoticiaFiltroDtoIn(

    @Length(max = NoticiaBusiness.CHAPEU_CARACTERES_MAXIMO)
    String chapeu,

    @Length(max = NoticiaBusiness.TITULO_CARACTERES_MAXIMO)
    String titulo,

    @Length(max = NoticiaBusiness.LINHA_FINA_CARACTERES_MAXIMO)
    String linhaFina,

    @Length(max = NoticiaBusiness.LIDE_CARACTERES_MAXIMO)
    String lide,

    @Length(max = NoticiaBusiness.CORPO_CARACTERES_MAXIMO)
    String corpo,

    @Length(max = NoticiaBusiness.NOME_AUTOR_CARACTERES_MAXIMO)
    String nomeAutor,

    @Length(max = NoticiaBusiness.FONTE_CARACTERES_MAXIMO)
    String fonte

) { }

