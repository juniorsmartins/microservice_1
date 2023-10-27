package io.pessoas_java.config.security.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountCredentialsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;
}

