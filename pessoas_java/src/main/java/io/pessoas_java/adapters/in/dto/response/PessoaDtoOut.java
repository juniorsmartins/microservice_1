package io.pessoas_java.adapters.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.pessoas_java.application.core.domain.enums.EstadoCivilEnum;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"chave", "nome", "sobrenome", "cpf", "dataNascimento", "sexo", "genero", "nivelEducacional", "nacionalidade", "usuario"})
public class PessoaDtoOut extends RepresentationModel<PessoaDtoOut> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("chave")
    private UUID key;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String dataNascimento;

    private String sexo;

    private String genero;

    private String nivelEducacional;

    private String nacionalidade;

    private EstadoCivilEnum estadoCivil;
}

