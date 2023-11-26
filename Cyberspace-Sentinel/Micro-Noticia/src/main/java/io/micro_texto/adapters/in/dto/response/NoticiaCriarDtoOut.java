package io.micro_texto.adapters.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoticiaCriarDtoOut(

    Long id,

    String chapeu,

    String titulo,

    String linhaFina,

    String lide,

    String corpo,

    String nomeAutor,

    String fonte
) { }

