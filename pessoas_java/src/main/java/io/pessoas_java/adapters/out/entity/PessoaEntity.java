package io.pessoas_java.adapters.out.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class PessoaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chave", nullable = false, unique = true, updatable = false)
    private UUID chave;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 50)
    private String sobrenome;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "nivel_educacional", nullable = false)
    private String nivelEducacional;

    @Column(name = "nacionalidade", nullable = false)
    private String nacionalidade;
}

