package io.pessoas_java.application.core.domain.filtro;

import java.util.UUID;

public final class PessoaFiltro {

    private UUID chave;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String sexo;

    private String genero;

    private String nivelEducacional;

    private String nacionalidade;

    private String estadoCivil;

    private TelefoneFiltro telefone;

    private EmailFiltro email;

    private EnderecoFiltro endereco;

    public UUID getChave() {
        return chave;
    }

    public void setChave(UUID chave) {
        this.chave = chave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNivelEducacional() {
        return nivelEducacional;
    }

    public void setNivelEducacional(String nivelEducacional) {
        this.nivelEducacional = nivelEducacional;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public TelefoneFiltro getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneFiltro telefone) {
        this.telefone = telefone;
    }

    public EmailFiltro getEmail() {
        return email;
    }

    public void setEmail(EmailFiltro email) {
        this.email = email;
    }

    public EnderecoFiltro getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoFiltro endereco) {
        this.endereco = endereco;
    }
}

