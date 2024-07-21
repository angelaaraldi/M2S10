package com.example.ProjetoCadastroMedicos.controller;

import com.example.ProjetoCadastroMedicos.controller.dto.MedicoGetRequest;
import com.example.ProjetoCadastroMedicos.controller.dto.MedicoGetResponse;
import com.example.ProjetoCadastroMedicos.controller.dto.MedicoRequest;
import com.example.ProjetoCadastroMedicos.controller.dto.MedicoResponse;
import com.example.ProjetoCadastroMedicos.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoService medicoService;
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postMedico(@Valid @RequestBody MedicoRequest request) {
       medicoService.salvarMedico(request);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void putMedico(@PathVariable Long id, @Valid @RequestBody MedicoRequest request) {
        medicoService.atualizarMedico(id, request);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<MedicoGetResponse> getMedicos(MedicoGetRequest filtros, Pageable paginacao) {
        return medicoService.buscarMedicos(filtros, paginacao);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicoResponse getMedico(@PathVariable Long id) {
        return medicoService.buscarMedico(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedico(@PathVariable Long id) {
        medicoService.deletarMedico(id);
    }
}
