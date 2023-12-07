package io.micro_noticia.application.core.domain;

import io.micro_noticia.config.exception.http_400.DadoComCampoNuloException;
import io.micro_noticia.config.exception.http_400.DadoComTamanhoInvalidoException;
import org.springframework.util.ObjectUtils;

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

    private Instant dataHoraEdicao;

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
            .ifPresentOrElse(capelo -> {
                    if (capelo.length() > 20) {
                        throw new DadoComTamanhoInvalidoException(String
                            .format("Chapéu possui limite máximo de 20 caracteres, mas você enviou %s.",
                                capelo.length()));
                    }
                    this.chapeu = capelo;
                },
                () -> {throw new DadoComCampoNuloException("O campo chapéu não pode ser nulo.");}
            );
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        Optional.ofNullable(titulo)
            .ifPresentOrElse(title -> {
                    if (title.length() > 100) {
                        throw new DadoComTamanhoInvalidoException(String
                            .format("Título possui limite máximo de 100 caracteres, mas você enviou %s.",
                                title.length()));
                    }
                    this.titulo = title;
                },
                () -> {throw new DadoComCampoNuloException("O campo título não pode ser nulo.");}
            );
    }

    public String getLinhaFina() {
        return linhaFina;
    }

    public void setLinhaFina(String linhaFina) {

        Optional.ofNullable(linhaFina)
            .ifPresentOrElse(linha -> {
                    if (linha.length() > 150) {
                        throw new DadoComTamanhoInvalidoException(String
                            .format("Linha Fina possui limite máximo de 150 caracteres, mas você enviou %s.",
                                linha.length()));
                    }
                    this.linhaFina = linha;
                },
                () -> {throw new DadoComCampoNuloException("O campo linhaFina não pode ser nulo.");}
            );
    }

    public String getLide() {
        return lide;
    }

    public void setLide(String lide) {

        Optional.ofNullable(lide)
            .ifPresentOrElse(lead -> {
                    if (lead.length() > 500) {
                        throw new DadoComTamanhoInvalidoException(String
                            .format("Lide possui limite máximo de 500 caracteres, mas você enviou %s.",
                                lead.length()));
                    }
                    this.lide = lead;
                },
                () -> {throw new DadoComCampoNuloException("O campo lide não pode ser nulo.");}
            );
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {

        Optional.ofNullable(corpo)
            .ifPresentOrElse(texto -> {
                    if (texto.length() > 5000) {
                        throw new DadoComTamanhoInvalidoException(String
                            .format("Corpo possui limite máximo de 5000 caracteres, mas você enviou %s.",
                                texto.length()));
                    }
                    this.corpo = texto;
                },
                () -> {throw new DadoComCampoNuloException("O campo texto não pode ser nulo.");}
            );
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {

        if (!ObjectUtils.isEmpty(nomeAutor) && nomeAutor.length() > 50) {
            throw new DadoComTamanhoInvalidoException(String
                .format("Nome do Autor possui limite máximo de 50 caracteres, mas você enviou %s.",
                    nomeAutor.length()));
        }
        this.nomeAutor = nomeAutor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {

        if (!ObjectUtils.isEmpty(fonte) && fonte.length() > 250) {
            throw new DadoComTamanhoInvalidoException(String
                .format("Fonte possui limite máximo de 250 caracteres, mas você enviou %s.",
                    fonte.length()));
        }
        this.fonte = fonte;
    }

    public Instant getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Instant dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Instant getDataHoraEdicao() {
        return dataHoraEdicao;
    }

    public void setDataHoraEdicao(Instant dataHoraEdicao) {
        this.dataHoraEdicao = dataHoraEdicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticiaBusiness that = (NoticiaBusiness) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getChapeu(), that.getChapeu()) && Objects.equals(getTitulo(), that.getTitulo()) && Objects.equals(getLinhaFina(), that.getLinhaFina()) && Objects.equals(getLide(), that.getLide()) && Objects.equals(getCorpo(), that.getCorpo()) && Objects.equals(getNomeAutor(), that.getNomeAutor()) && Objects.equals(getFonte(), that.getFonte());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getChapeu(), getTitulo(), getLinhaFina(), getLide(), getCorpo(), getNomeAutor(), getFonte());
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
                '}';
    }
}

