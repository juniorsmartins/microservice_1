package io.pessoas_java.config.security.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TokenDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    private Boolean authenticated;

    private Date created;

    private Date expiration;

    private String accessToken;

    private String refreshToken;
}

