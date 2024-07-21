package com.example.ProjetoCadastroMedicos.entity;

import com.example.ProjetoCadastroMedicos.enums.Especialidade;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "medico")
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String crm;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private String telefone;
    @Enumerated(value = EnumType.STRING)
    private Especialidade especialidade;

    public MedicoEntity() {
    }

    public MedicoEntity(Long id, String nome, String crm, LocalDate dataNascimento, String telefone, Especialidade especialidade) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
