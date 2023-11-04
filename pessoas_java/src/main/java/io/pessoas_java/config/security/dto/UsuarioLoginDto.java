package io.pessoas_java.config.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioLoginDto {

    @NotBlank
    @Email(regexp = "^[a-z0-9_+.]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String username;

    @NotBlank
    @Size(min = 6, max = 6)
    private String password;
}

