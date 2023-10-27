package io.pessoas_java.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
public class PessoaDtoOut implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID chave;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String dataNascimento;

    private String sexo;

    private String genero;

    private String nivelEducacional;

    private String nacionalidade;
}

