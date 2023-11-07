package io.pessoas_java.adapters.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Builder
public record PessoaDtoIn(

        @NotBlank
        @Length(max = 30)
        String nome,

        @NotBlank
        @Length(max = 50)
        String sobrenome,

        @CPF
        @NotBlank
        @Length(max = 11)
        String cpf,

        @NotBlank
        String dataNascimento,

        @NotBlank
        String sexo,

        String genero,

        @NotBlank
        String nivelEducacional,

        @NotBlank
        String nacionalidade,

        @NotNull
        String estadoCivil
) { }

