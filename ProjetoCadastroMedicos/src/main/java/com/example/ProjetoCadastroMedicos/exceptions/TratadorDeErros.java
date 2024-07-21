package com.example.ProjetoCadastroMedicos.exceptions;

import com.example.ProjetoCadastroMedicos.controller.dto.ErroValidacaoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> trataEntidadeNaoEncontrada() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErroValidacaoDTO> trataChaveDuplicada(DuplicateKeyException exception) {
        ErroValidacaoDTO erro = new ErroValidacaoDTO();
        erro.setCampo("crm");
        erro.setMensagem(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDTO>> trataParametroInvalido(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErroValidacaoDTO::new).toList());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroValidacaoDTO> trataEnumDataInvalida(HttpMessageNotReadableException exception) {
        ErroValidacaoDTO response = new ErroValidacaoDTO();
        if (exception.getLocalizedMessage().contains("Especialidade")) {
            response.setCampo("especialidade");
        } else if (exception.getLocalizedMessage().contains("LocalDate")) {
            response.setCampo("dataNascimento");
        }
        response.setMensagem(exception.getLocalizedMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
