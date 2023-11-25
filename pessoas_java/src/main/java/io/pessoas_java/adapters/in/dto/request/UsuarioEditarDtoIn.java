package io.pessoas_java.adapters.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UsuarioEditarDtoIn(

    @NotNull
    @Positive
    Long id,

    @NotBlank
    @Size(min = 5, max = 50)
    String username,

    @NotBlank
    @Size(min = 5, max = 50)
    String password
) { }

