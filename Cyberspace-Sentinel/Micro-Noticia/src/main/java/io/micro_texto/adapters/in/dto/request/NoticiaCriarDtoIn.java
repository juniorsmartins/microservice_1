package io.micro_texto.adapters.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record NoticiaCriarDtoIn(

    @NotBlank
    @Length(max = 20)
    String chapeu,

    @NotBlank
    @Length(max = 100)
    String titulo,

    @NotBlank
    @Length(max = 150)
    String linhaFina,

    @NotBlank
    @Length(max = 500)
    String lide,

    @NotBlank
    @Length(max = 5000)
    String corpo,

    @Length(max = 50)
    String nomeAutor,

    @Length(max = 250)
    String fonte
) { }

