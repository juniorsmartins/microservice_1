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
                this.validarCampo("Chapéu", boina, 20);
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
                this.validarCampo("Título", title, 100);
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
                this.validarCampo("Linha Fina", linha, 150);
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
                this.validarCampo("Lide", lead, 500);
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
                    this.validarCampo("Corpo", corpus, 5000);
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

    private void validarCampo(String nomeCampo, String valorCampo, int tamanhoMaximno) {
        if (valorCampo.isBlank()) {
            throw new CampoVazioProibidoException(nomeCampo);
        }
        if (valorCampo.length() > tamanhoMaximno) {
            throw new DadoComTamanhoMaximoInvalidoException(nomeCampo, tamanhoMaximno, valorCampo.length());
        }
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

