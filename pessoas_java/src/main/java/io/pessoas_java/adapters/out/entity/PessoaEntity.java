package io.pessoas_java.adapters.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoas")
@Getter
@Setter
public final class PessoaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

