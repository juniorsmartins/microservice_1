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
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class EnderecoPesquisarDtoOut implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String pais;

    private String cep;

    private String estado;

    private String cidade;

    private String bairro;

    private String logradouro;

    private String numero;

    private String complemento;
}

