package io.pessoas_java.adapters.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "enderecos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class EnderecoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pais", nullable = false, length = 40)
    private String pais;

    @Column(name = "cep", nullable = false, length = 40)
    private String cep;

    @Column(name = "estado", nullable = false, length = 40)
    private String estado;

    @Column(name = "cidade", nullable = false, length = 40)
    private String cidade;

    @Column(name = "bairro", nullable = false, length = 40)
    private String bairro;

    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "complemento", length = 250)
    private String complemento;

    @OneToOne(mappedBy = "endereco")
    private PessoaEntity pessoa;
}

