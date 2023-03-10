package br.com.dio.academiadigital.controller;

import br.com.dio.academiadigital.model.dto.AlunoCreateRequest;
import br.com.dio.academiadigital.model.dto.AlunoResponse;
import br.com.dio.academiadigital.model.dto.AlunoUpdateRequest;
import br.com.dio.academiadigital.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> getAll() {
        List<AlunoResponse> alunos = service.getAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable("id") Long id) {
        AlunoResponse aluno = service.getById(id);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> create(@Valid @RequestBody AlunoCreateRequest request) {
        AlunoResponse novoAluno = service.create(request);
        return new ResponseEntity<>(novoAluno, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoResponse> update(@PathVariable("id") Long id,
                                                @Valid
                                                @RequestBody AlunoUpdateRequest request) {
        AlunoResponse alunoAtualizado = service.update(id, request);
        return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
