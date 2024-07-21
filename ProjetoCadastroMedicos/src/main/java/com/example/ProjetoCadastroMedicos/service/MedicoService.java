package com.example.ProjetoCadastroMedicos.service;

import com.example.ProjetoCadastroMedicos.controller.dto.MedicoGetRequest;
import com.example.ProjetoCadastroMedicos.controller.dto.MedicoGetResponse;
import com.example.ProjetoCadastroMedicos.controller.dto.MedicoRequest;
import com.example.ProjetoCadastroMedicos.controller.dto.MedicoResponse;
import com.example.ProjetoCadastroMedicos.entity.MedicoEntity;
import com.example.ProjetoCadastroMedicos.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    private final MedicoRepository repository;
    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }
    public void salvarMedico(MedicoRequest request) {
        if (repository.existsByCrm(request.getCrm())) {
            throw new DuplicateKeyException("Já existe um médico com esse CRM: " + request.getCrm());
        } else {
            MedicoEntity entity = new MedicoEntity();
            entity.setNome(request.getNome());
            entity.setCrm(request.getCrm());
            entity.setDataNascimento(request.getDataNascimento());
            entity.setTelefone(request.getTelefone());
            entity.setEspecialidade(request.getEspecialidade());
            repository.save(entity);
        }
    }
    public void atualizarMedico(Long id, MedicoRequest request) {
        MedicoEntity medico = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (medico != null) {
            if (repository.existsByCrm(request.getCrm()) && repository.findByCrm(request.getCrm()) != medico) {
                throw new DuplicateKeyException("Já existe um médico com esse CRM: " + request.getCrm());
            } else {
                medico.setNome(request.getNome());
                medico.setCrm(request.getCrm());
                medico.setDataNascimento(request.getDataNascimento());
                medico.setTelefone(request.getTelefone());
                medico.setEspecialidade(request.getEspecialidade());
                repository.save(medico);
            }
        }
    }
    public Page<MedicoGetResponse> buscarMedicos(MedicoGetRequest filtros, Pageable paginacao) {
        String filtroNome = filtros.getNome() != null ? filtros.getNome() : "";
        if (filtros.getEspecialidade() == null && filtros.getDataNascimento() == null) {
            return repository.findByNomeContainingIgnoreCase(filtroNome, paginacao).map(medico -> {
                MedicoGetResponse medicoResponse = new MedicoGetResponse();
                medicoResponse.setNome(medico.getNome());
                medicoResponse.setDataNascimento(medico.getDataNascimento());
                medicoResponse.setEspecialidade(medico.getEspecialidade());
                return medicoResponse;
            });
        }
        if (filtros.getDataNascimento() == null) {
            return repository.findByNomeContainingIgnoreCaseAndEspecialidade(filtroNome, filtros.getEspecialidade(), paginacao).map(medico -> {
                MedicoGetResponse medicoResponse = new MedicoGetResponse();
                medicoResponse.setNome(medico.getNome());
                medicoResponse.setDataNascimento(medico.getDataNascimento());
                medicoResponse.setEspecialidade(medico.getEspecialidade());
                return medicoResponse;
            });
        }
        if (filtros.getEspecialidade() == null) {
            return repository.findByNomeContainingIgnoreCaseAndDataNascimento(filtroNome, filtros.getDataNascimento(), paginacao).map(medico -> {
                MedicoGetResponse medicoResponse = new MedicoGetResponse();
                medicoResponse.setNome(medico.getNome());
                medicoResponse.setDataNascimento(medico.getDataNascimento());
                medicoResponse.setEspecialidade(medico.getEspecialidade());
                return medicoResponse;
            });
        }
        return repository.findByNomeContainingIgnoreCaseAndEspecialidadeAndDataNascimento(filtroNome, filtros.getEspecialidade(), filtros.getDataNascimento(), paginacao).map(medico -> {
            MedicoGetResponse medicoResponse = new MedicoGetResponse();
            medicoResponse.setNome(medico.getNome());
            medicoResponse.setDataNascimento(medico.getDataNascimento());
            medicoResponse.setEspecialidade(medico.getEspecialidade());
            return medicoResponse;
        });
    }
    public MedicoResponse buscarMedico(Long id) {
        MedicoEntity medico = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (medico != null) {
            return new MedicoResponse(medico.getId(), medico.getNome(), medico.getCrm(), medico.getDataNascimento(), medico.getTelefone(), medico.getEspecialidade());
        }
        return null;
    }
    public void deletarMedico(Long id) {
        MedicoEntity medico = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (medico != null) {
            repository.delete(medico);
        }
    }
}
