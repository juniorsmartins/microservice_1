package io.pessoas_java.adapters.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EnderecoCadastrarDtoOut(

    Long id,

    String pais,

    String cep,

    String estado,

    String cidade,

    String bairro,

    String logradouro,

    String numero,

    String complemento
) { }

