package io.micronoticias.application.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronoticias.config.exception.http_400.CampoNuloProibidoException;
import io.micronoticias.config.exception.http_400.CampoVazioProibidoException;
import io.micronoticias.config.exception.http_400.DadoComTamanhoMaximoInvalidoException;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class NoticiaBusiness {

    public static final int CHAPEU_CARACTERES_MAXIMO = 20;

    public static final int TITULO_CARACTERES_MAXIMO = 100;

    public static final int LINHA_FINA_CARACTERES_MAXIMO = 150;

    public static final int LIDE_CARACTERES_MAXIMO = 500;

    public static final int CORPO_CARACTERES_MAXIMO = 5000;

    public static final int NOME_AUTOR_CARACTERES_MAXIMO = 50;

    public static final int FONTE_CARACTERES_MAXIMO = 250;

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
        final String CHAPEU = "Chapéu";

        Optional.ofNullable(chapeu)
            .ifPresentOrElse(boina -> {
                this.validarCampo(CHAPEU, boina, CHAPEU_CARACTERES_MAXIMO);
                this.chapeu = boina;
            },
            () -> {throw new CampoNuloProibidoException(CHAPEU);}
        );
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        final String TITULO = "Título";

        Optional.ofNullable(titulo)
            .ifPresentOrElse(title -> {
                this.validarCampo(TITULO, title, TITULO_CARACTERES_MAXIMO);
                this.titulo = title;
            },
            () -> {throw new CampoNuloProibidoException(TITULO);}
        );
    }

    public String getLinhaFina() {
        return linhaFina;
    }

    public void setLinhaFina(String linhaFina) {
        final String LINHA_FINA = "Linha Fina";

        Optional.ofNullable(linhaFina)
            .ifPresentOrElse(linha -> {
                this.validarCampo(LINHA_FINA, linha, LINHA_FINA_CARACTERES_MAXIMO);
                this.linhaFina = linha;
            },
            () -> {throw new CampoNuloProibidoException(LINHA_FINA);}
        );
    }

    public String getLide() {
        return lide;
    }

    public void setLide(String lide) {
        final String LIDE = "Lide";

        Optional.ofNullable(lide)
            .ifPresentOrElse(lead -> {
                this.validarCampo(LIDE, lead, LIDE_CARACTERES_MAXIMO);
                this.lide = lead;
            },
            () -> {throw new CampoNuloProibidoException(LIDE);}
        );
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        final String CORPO = "Corpo";

        Optional.ofNullable(corpo)
            .ifPresentOrElse(corpus -> {
                    this.validarCampo(CORPO, corpus, CORPO_CARACTERES_MAXIMO);
                    this.corpo = corpus;
                },
                () -> {throw new CampoNuloProibidoException(CORPO);}
            );
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        if (Objects.nonNull(nomeAutor) && nomeAutor.length() > NOME_AUTOR_CARACTERES_MAXIMO) {
            throw new DadoComTamanhoMaximoInvalidoException("Nome do Autor", NOME_AUTOR_CARACTERES_MAXIMO,
                nomeAutor.length());
        }
        this.nomeAutor = nomeAutor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        if (Objects.nonNull(fonte) && fonte.length() > FONTE_CARACTERES_MAXIMO) {
            throw new DadoComTamanhoMaximoInvalidoException("Fonte", FONTE_CARACTERES_MAXIMO, fonte.length());
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

