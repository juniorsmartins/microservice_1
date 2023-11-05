package io.pessoas_java.config.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDtoIn(

    @NotBlank
    @Email(regexp = "^[a-z0-9_+.]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    String username,

    @NotBlank
    @Size(min = 6, max = 6)
    String password
) { }

