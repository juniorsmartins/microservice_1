package io.pessoas_java.adapters.in.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PessoaEditarDtoIn(

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

