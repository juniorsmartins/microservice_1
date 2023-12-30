package io.micronoticias.application.core.domain.filtro;

public final class NoticiaFiltro {

    private String id;

    private String chapeu;

    private String titulo;

    private String linhaFina;

    private String lide;

    private String corpo;

    private String nomeAutor;

    private String fonte;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}

