package io.pessoas_java.adapters.in.dto.filtro;

import java.util.UUID;

public record PessoaDtoFiltro(

        UUID chave,

        String nome,

        String sobrenome,

        String cpf,

        String sexo,

        String genero,

        String nivelEducacional,

        String nacionalidade,

        String estadoCivil,

        TelefoneDtoFiltro telefone,

        EmailDtoFiltro email,

        EnderecoDtoFiltro endereco
) { }

