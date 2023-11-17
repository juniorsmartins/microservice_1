package io.pessoas_java.adapters.in.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record EmailEditarDtoIn(

    @NotNull
    @Positive
    Long id,

    @NotBlank
    @Email
    @Size(max = 150)
    String email
) { }

