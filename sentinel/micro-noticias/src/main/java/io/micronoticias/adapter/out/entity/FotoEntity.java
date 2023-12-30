package io.micronoticias.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "fotos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class FotoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Lob
    @Column(name = "imagem", length = Length.LOB_DEFAULT)
    private byte[] imagem;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "tamanho")
    private String tamanho;
}

