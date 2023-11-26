package io.micro_texto.application.core.domain;

import java.util.Objects;

public final class NoticiaBusiness {

    private Long id;

    private String chapeu;

    private String titulo;

    private String linhaFina;

    private String lide;

    private String corpo;

    private String nomeAutor;

    private String fonte;

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
        this.chapeu = chapeu;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLinhaFina() {
        return linhaFina;
    }

    public void setLinhaFina(String linhaFina) {
        this.linhaFina = linhaFina;
    }

    public String getLide() {
        return lide;
    }

    public void setLide(String lide) {
        this.lide = lide;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
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

