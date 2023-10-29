package io.pessoas_java.config.security.dto;

import io.pessoas_java.config.security.entity.value_object.UserRole;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterDto(

    @NotBlank
    @Length(max = 150)
    String login,

    @NotBlank
    @Length(max = 512)
    String password,

    @NotBlank
    UserRole role
) { }

