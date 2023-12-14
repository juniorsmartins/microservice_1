package io.micronoticias.adapter.in.dto.request;

import io.micronoticias.application.core.domain.NoticiaBusiness;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record NoticiaCriarDtoIn(

    @NotBlank
    @Length(max = NoticiaBusiness.CHAPEU_CARACTERES_MAXIMO)
    String chapeu,

    @NotBlank
    @Length(max = NoticiaBusiness.TITULO_CARACTERES_MAXIMO)
    String titulo,

    @NotBlank
    @Length(max = NoticiaBusiness.LINHA_FINA_CARACTERES_MAXIMO)
    String linhaFina,

    @NotBlank
    @Length(max = NoticiaBusiness.LIDE_CARACTERES_MAXIMO)
    String lide,

    @NotBlank
    @Length(max = NoticiaBusiness.CORPO_CARACTERES_MAXIMO)
    String corpo,

    @Length(max = NoticiaBusiness.NOME_AUTOR_CARACTERES_MAXIMO)
    String nomeAutor,

    @Length(max = NoticiaBusiness.FONTE_CARACTERES_MAXIMO)
    String fonte

) { }

