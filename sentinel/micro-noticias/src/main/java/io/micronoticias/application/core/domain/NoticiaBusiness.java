package io.micronoticias.application.core.domain;

import io.micronoticias.config.exception.http_400.CampoNuloProibidoException;
import io.micronoticias.config.exception.http_400.CampoVazioProibidoException;
import io.micronoticias.config.exception.http_400.DadoComTamanhoMaximoInvalidoException;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public final class NoticiaBusiness {

    private Long id;

    private String chapeu;

    private String titulo;

    private String linhaFina;

    private String lide;

    private String corpo;

    private String nomeAutor;

    private String fonte;

    private Instant dataHoraCriacao;

    private Instant dataHoraAtualizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChapeu() {
        return chapeu;
    }

    public void setChapeu(String chapeu) {
        Optional.ofNullable(chapeu)
            .ifPresentOrElse(boina -> {
                if (boina.isBlank()) {
                    throw new CampoVazioProibidoException("Chapéu");
                }
                if (boina.length() > 20) {
                    throw new DadoComTamanhoMaximoInvalidoException("Chapéu", 20, boina.length());
                }
                this.chapeu = boina;
            },
            () -> {throw new CampoNuloProibidoException("Chapéu");}
        );
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        Optional.ofNullable(titulo)
            .ifPresentOrElse(title -> {
                if (title.isBlank()) {
                    throw new CampoVazioProibidoException("Título");
                }
                if (title.length() > 100) {
                    throw new DadoComTamanhoMaximoInvalidoException("Título", 100, title.length());
                }
                this.titulo = title;
            },
            () -> {throw new CampoNuloProibidoException("Título");}
        );
    }

    public String getLinhaFina() {
        return linhaFina;
    }

    public void setLinhaFina(String linhaFina) {
        Optional.ofNullable(linhaFina)
            .ifPresentOrElse(linha -> {
                if (linha.isBlank()) {
                    throw new CampoVazioProibidoException("Linha Fina");
                }
                if (linha.length() > 150) {
                    throw new DadoComTamanhoMaximoInvalidoException("Linha Fina", 150, linha.length());
                }
                this.linhaFina = linha;
            },
            () -> {throw new CampoNuloProibidoException("Linha Fina");}
        );
    }

    public String getLide() {
        return lide;
    }

    public void setLide(String lide) {
        Optional.ofNullable(lide)
            .ifPresentOrElse(lead -> {
                if (lead.isBlank()) {
                    throw new CampoVazioProibidoException("Lide");
                }
                if (lead.length() > 500) {
                    throw new DadoComTamanhoMaximoInvalidoException("Lide", 500, lead.length());
                }
                this.lide = lead;
            },
            () -> {throw new CampoNuloProibidoException("Lide");}
        );
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        Optional.ofNullable(corpo)
            .ifPresentOrElse(corpus -> {
                    if (corpus.isBlank()) {
                        throw new CampoVazioProibidoException("Corpo");
                    }
                    if (corpus.length() > 5000) {
                        throw new DadoComTamanhoMaximoInvalidoException("Corpo", 5000, corpus.length());
                    }
                    this.corpo = corpus;
                },
                () -> {throw new CampoNuloProibidoException("Corpo");}
            );
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        if (Objects.nonNull(nomeAutor) && nomeAutor.length() > 50) {
            throw new DadoComTamanhoMaximoInvalidoException("Nome do Autor", 50, nomeAutor.length());
        }
        this.nomeAutor = nomeAutor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        if (Objects.nonNull(fonte) && fonte.length() > 250) {
            throw new DadoComTamanhoMaximoInvalidoException("Fonte", 250, fonte.length());
        }
        this.fonte = fonte;
    }

    public Instant getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Instant dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Instant getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Instant dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticiaBusiness that = (NoticiaBusiness) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "NoticiaBusiness{" +
                "id=" + id +
                ", chapeu='" + chapeu + '\'' +
                ", titulo='" + titulo + '\'' +
                ", linhaFina='" + linhaFina + '\'' +
                ", lide='" + lide + '\'' +
                ", corpo='" + corpo + '\'' +
                ", nomeAutor='" + nomeAutor + '\'' +
                ", fonte='" + fonte + '\'' +
                ", dataHoraCriacao=" + dataHoraCriacao +
                ", dataHoraAtualizacao=" + dataHoraAtualizacao +
                '}';
    }
}

