package io.pessoas_java.adapters.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"email"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class EmailPesquisarDtoOut implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
}

