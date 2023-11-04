package io.pessoas_java.config.security.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioLogin {

    private String username;

    private String password;
}

