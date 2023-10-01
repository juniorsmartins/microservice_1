package io.pessoas_java.adapters.in.dto.request;

public record PessoaDtoIn(

        String nome,

        String sobrenome,

        String cpf,

        String dataNascimento,

        String sexo,

        String genero,

        String nivelEducacional,

        String nacionalidade
) { }

