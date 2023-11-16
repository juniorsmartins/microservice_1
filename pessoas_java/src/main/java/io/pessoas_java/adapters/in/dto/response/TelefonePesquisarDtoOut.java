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
@EqualsAndHashCode(of = {"numero"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TelefonePesquisarDtoOut implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String numero;
}

