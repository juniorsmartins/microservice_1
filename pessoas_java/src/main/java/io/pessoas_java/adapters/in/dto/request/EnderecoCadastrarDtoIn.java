package io.pessoas_java.adapters.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record EnderecoCadastrarDtoIn(

    @NotBlank
    @Size(max = 40)
    String pais,

    @NotBlank
    @Size(max = 40)
    String cep,

    @NotBlank
    @Size(max = 40)
    String estado,

    @NotBlank
    @Size(max = 40)
    String cidade,

    @NotBlank
    @Size(max = 40)
    String bairro,

    @NotBlank
    @Size(max = 100)
    String logradouro,

    @Size(max = 20)
    String numero,

    @Size(max = 250)
    String complemento
) { }

