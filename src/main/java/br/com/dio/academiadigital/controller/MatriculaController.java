package br.com.dio.academiadigital.controller;

import br.com.dio.academiadigital.model.dto.MatriculaRequest;
import br.com.dio.academiadigital.model.dto.MatriculaResponse;
import br.com.dio.academiadigital.service.impl.MatriculaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaServiceImpl service;

    public MatriculaController(MatriculaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponse>> getAll(@RequestParam(value = "bairro", required = false) String bairro) {
        List<MatriculaResponse> matriculas = service.getAll(bairro);
        return new ResponseEntity<>(matriculas, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MatriculaResponse> getById(@PathVariable("id") Long id) {
        MatriculaResponse matricula = service.getById(id);
        return new ResponseEntity<>(matricula, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatriculaResponse> create(@Valid @RequestBody MatriculaRequest request) {
        MatriculaResponse novaMatricula = service.create(request);
        return new ResponseEntity<>(novaMatricula, HttpStatus.CREATED);
    }
}
