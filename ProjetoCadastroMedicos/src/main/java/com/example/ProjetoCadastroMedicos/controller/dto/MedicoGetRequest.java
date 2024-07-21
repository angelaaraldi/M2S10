package com.example.ProjetoCadastroMedicos.controller.dto;

import com.example.ProjetoCadastroMedicos.enums.Especialidade;

import java.time.LocalDate;

public class MedicoGetRequest {
    private String nome;
    private Especialidade especialidade;
    private LocalDate dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
