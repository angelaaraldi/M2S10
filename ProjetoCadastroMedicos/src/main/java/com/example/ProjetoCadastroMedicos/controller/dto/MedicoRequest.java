package com.example.ProjetoCadastroMedicos.controller.dto;

import com.example.ProjetoCadastroMedicos.enums.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MedicoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String crm;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotBlank
    private String telefone;
    @NotNull
    private Especialidade especialidade;

    public MedicoRequest() {
    }

    public MedicoRequest(String nome, String crm, LocalDate dataNascimento, String telefone, Especialidade especialidade) {
        this.nome = nome;
        this.crm = crm;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank String getCrm() {
        return crm;
    }

    public void setCrm(@NotBlank String crm) {
        this.crm = crm;
    }

    public @NotNull LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @NotBlank String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank String telefone) {
        this.telefone = telefone;
    }

    public @NotNull Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(@NotNull Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
