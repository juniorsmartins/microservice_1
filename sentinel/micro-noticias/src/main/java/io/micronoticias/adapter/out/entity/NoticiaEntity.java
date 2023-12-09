package io.micronoticias.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "noticias")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class NoticiaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chapeu", nullable = false, length = 20)
    private String chapeu;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "linha_fina", nullable = false, length = 150)
    private String linhaFina;

    @Lob
    @Column(name = "lide", nullable = false, length = 500, columnDefinition = "TEXT")
    private String lide;

    @Lob
    @Column(name = "corpo", nullable = false, length = 5000, columnDefinition = "TEXT")
    private String corpo;

    @Column(name = "nome_autor", length = 50)
    private String nomeAutor;

    @Column(name = "fonte", length = 250)
    private String fonte;

    @Column(name = "data_hora_criacao", insertable = true, updatable = false, nullable = false)
    private Instant dataHoraCriacao;

    @Column(name = "data_hora_atualizacao", insertable = false, updatable = true, nullable = true)
    private Instant dataHoraAtualizacao;

    @PrePersist
    private void acionarAntesDeCriar() {
        this.dataHoraCriacao = Instant.now();
    }

    @PreUpdate
    private void acionarAntesDeAtualizar() {
        this.dataHoraAtualizacao = Instant.now();
    }
}

