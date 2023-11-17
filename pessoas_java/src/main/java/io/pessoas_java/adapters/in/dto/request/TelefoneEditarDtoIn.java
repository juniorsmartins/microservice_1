package io.pessoas_java.adapters.in.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record TelefoneEditarDtoIn(

    @NotNull
    @Positive
    Long id,

    @NotNull
    @Size(min = 10, max = 11)
    String numero
) { }

