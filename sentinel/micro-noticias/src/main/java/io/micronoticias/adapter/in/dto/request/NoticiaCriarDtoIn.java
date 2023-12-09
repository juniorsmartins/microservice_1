package io.micronoticias.adapter.in.dto.request;

import lombok.Builder;

@Builder
public record NoticiaCriarDtoIn(

    String chapeu,

    String titulo,

    String linhaFina,

    String lide,

    String corpo,

    String nomeAutor,

    String fonte

) { }

