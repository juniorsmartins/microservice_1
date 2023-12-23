package io.micronoticias.adapter.out.entity;

import io.micronoticias.application.core.domain.NoticiaBusiness;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "noticia_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_noticia_foto"))
    private List<FotoEntity> fotos;

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

    public static NoticiaEntity converterParaEntity(NoticiaBusiness noticiaBusiness) {
        return NoticiaEntity.builder()
            .id(noticiaBusiness.getId())
            .chapeu(noticiaBusiness.getChapeu())
            .titulo(noticiaBusiness.getTitulo())
            .linhaFina(noticiaBusiness.getLinhaFina())
            .lide(noticiaBusiness.getLide())
            .corpo(noticiaBusiness.getCorpo())
            .nomeAutor(noticiaBusiness.getNomeAutor())
            .fonte(noticiaBusiness.getFonte())
            .dataHoraCriacao(noticiaBusiness.getDataHoraCriacao())
            .dataHoraAtualizacao(noticiaBusiness.getDataHoraAtualizacao())
            .build();
    }

    public static NoticiaBusiness converterParaBusiness(NoticiaEntity noticiaEntity) {

        var noticiaBusiness = new NoticiaBusiness();
        noticiaBusiness.setId(noticiaEntity.getId());
        noticiaBusiness.setChapeu(noticiaEntity.getChapeu());
        noticiaBusiness.setTitulo(noticiaEntity.getTitulo());
        noticiaBusiness.setLinhaFina(noticiaEntity.getLinhaFina());
        noticiaBusiness.setLide(noticiaEntity.getLide());
        noticiaBusiness.setCorpo(noticiaEntity.getCorpo());
        noticiaBusiness.setNomeAutor(noticiaEntity.getNomeAutor());
        noticiaBusiness.setFonte(noticiaEntity.getFonte());
        noticiaBusiness.setDataHoraCriacao(noticiaEntity.getDataHoraCriacao());
        noticiaBusiness.setDataHoraAtualizacao(noticiaEntity.getDataHoraAtualizacao());

        return noticiaBusiness;
    }
}

