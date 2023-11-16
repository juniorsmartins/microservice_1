package io.pessoas_java.adapters.in.dto.filtro;

public record EnderecoDtoFiltro(

    String pais,

    String cep,

    String estado,

    String cidade,

    String bairro,

    String logradouro,

    String numero,

    String complemento
) { }

