package io.pessoas_java.adapters.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PessoaDtoOut(

        UUID chave,

        String nome,

        String sobrenome,

        String cpf,

        String dataNascimento,

        String sexo,

        String genero,

        String nivelEducacional,

        String nacionalidade
) { }

