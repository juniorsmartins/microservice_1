package io.pessoas_java.adapters.in.dto.request;

import java.util.UUID;

public record PessoaDtoFiltro(

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

