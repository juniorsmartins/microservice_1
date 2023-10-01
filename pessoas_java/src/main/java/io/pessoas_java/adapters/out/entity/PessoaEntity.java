package io.pessoas_java.adapters.out.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Getter
@Setter
public final class PessoaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private UUID chave;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String dataNascimento;

    private String sexo;

    private String genero;

    private String nivelEducacional;

    private String nacionalidade;
}

