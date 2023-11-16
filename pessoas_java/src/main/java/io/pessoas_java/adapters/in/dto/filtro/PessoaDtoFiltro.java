package io.pessoas_java.adapters.in.dto.filtro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class PessoaDtoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID chave;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String sexo;

    private String genero;

    private String nivelEducacional;

    private String nacionalidade;

    private String estadoCivil;

    private TelefoneDtoFiltro telefones;

    private EmailDtoFiltro emails;

    private EnderecoDtoFiltro endereco;
}

