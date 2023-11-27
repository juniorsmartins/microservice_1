package io.micro_texto.adapters.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoticiaCriarDtoOut(

        Long id,

        String chapeu,

        String titulo,

        String linhaFina,

        String lide,

        String corpo,

        String nomeAutor,

        String fonte,

        Instant dataHoraCriacao,

        Instant dataHoraEdicao
) { }

