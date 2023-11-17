package io.pessoas_java.adapters.in.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record EmailEditarDtoIn(

    Long id,

    @NotBlank
    @Email
    @Size(max = 150)
    String email
) { }

