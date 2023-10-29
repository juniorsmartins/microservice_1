package io.pessoas_java.config.security.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AuthenticationDto(

    @NotBlank
    @Length(max = 150)
    String login,

    @NotBlank
    @Length(max = 512)
    String password
) {
}

