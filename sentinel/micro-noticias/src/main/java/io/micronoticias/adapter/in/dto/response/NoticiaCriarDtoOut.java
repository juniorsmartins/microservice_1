package io.micronoticias.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;

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

        Instant dataHoraAtualizacao

) { }

