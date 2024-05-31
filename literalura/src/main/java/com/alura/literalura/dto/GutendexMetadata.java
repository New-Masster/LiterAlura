package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexMetadata {
    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private int anoNascimento;

    @JsonAlias("death_year")
    private Integer anoMorte;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    @Override
    public String toString() {
        return "GutendexMetadata{" +
                "nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoMorte=" + anoMorte +
                '}';
    }
}
