package io.pessoas_java.adapters.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UsuarioCadastrarDtoIn(

    @NotBlank
    @Size(min = 5, max = 50)
    String username,

    @NotBlank
    @Size(min = 5, max = 50)
    String password
) { }

