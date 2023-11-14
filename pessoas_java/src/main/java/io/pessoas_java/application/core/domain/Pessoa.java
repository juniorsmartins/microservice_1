package io.pessoas_java.application.core.domain;

import io.pessoas_java.application.core.domain.enums.EstadoCivilEnum;
import io.pessoas_java.application.core.domain.enums.NivelEducacionalEnum;
import io.pessoas_java.application.core.domain.enums.SexoEnum;
import io.pessoas_java.application.core.domain.value_object.CadastroPessoaFisica;
import io.pessoas_java.application.core.domain.value_object.CorreioEletronico;
import io.pessoas_java.application.core.domain.value_object.Endereco;
import io.pessoas_java.application.core.domain.value_object.Telefone;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public final class Pessoa {

    private Long id;

    private UUID chave = UUID.randomUUID();

    private String nome;

    private String sobrenome;

    private CadastroPessoaFisica cpf;

    private LocalDate dataNascimento;

    private SexoEnum sexo;

    private String genero;

    private NivelEducacionalEnum nivelEducacional;

    private String nacionalidade;

    private EstadoCivilEnum estadoCivil;

    private Set<Telefone> telefones;

    private Set<CorreioEletronico> correioEletronicos;

    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return this.cpf.getCpf();
    }

    public void setCpf(String cpf) {
        this.cpf = new CadastroPessoaFisica(cpf);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public NivelEducacionalEnum getNivelEducacional() {
        return nivelEducacional;
    }

    public void setNivelEducacional(NivelEducacionalEnum nivelEducacional) {
        this.nivelEducacional = nivelEducacional;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public EstadoCivilEnum getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Set<CorreioEletronico> getEmails() {
        return correioEletronicos;
    }

    public void setEmails(Set<CorreioEletronico> correioEletronicos) {
        this.correioEletronicos = correioEletronicos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", chave=" + chave +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf=" + cpf +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                ", genero='" + genero + '\'' +
                ", nivelEducacional=" + nivelEducacional +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", estadoCivil=" + estadoCivil +
                ", telefones=" + telefones +
                ", correioEletronicos=" + correioEletronicos +
                ", endereco=" + endereco +
                '}';
    }
}

