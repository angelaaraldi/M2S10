package com.example.ProjetoCadastroMedicos.repository;

import com.example.ProjetoCadastroMedicos.entity.MedicoEntity;
import com.example.ProjetoCadastroMedicos.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
   Page<MedicoEntity> findByNomeContainingIgnoreCase(String nome, Pageable paginacao);
   Page<MedicoEntity> findByNomeContainingIgnoreCaseAndEspecialidade(String nome, Especialidade especialidade, Pageable paginacao);
   Page<MedicoEntity> findByNomeContainingIgnoreCaseAndDataNascimento(String nome, LocalDate dataNascimento, Pageable paginacao);
   Page<MedicoEntity> findByNomeContainingIgnoreCaseAndEspecialidadeAndDataNascimento(String nome, Especialidade especialidade, LocalDate dataNascimento, Pageable paginacao);
   boolean existsByCrm(String crm);
   MedicoEntity findByCrm(String crm);
}