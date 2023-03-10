package br.com.dio.academiadigital.controller;

import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaCreateRequest;
import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaResponse;
import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaUpdateRequest;
import br.com.dio.academiadigital.service.impl.AvaliacaoFisicaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {
    private final AvaliacaoFisicaServiceImpl service;

    public AvaliacaoFisicaController(AvaliacaoFisicaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoFisicaResponse>> getAll() {
        List<AvaliacaoFisicaResponse> avaliacoes = service.getAll();
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AvaliacaoFisicaResponse> getById(@PathVariable("id") Long id) {
        AvaliacaoFisicaResponse avaliacao = service.getById(id);
        return new ResponseEntity<>(avaliacao, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AvaliacaoFisicaResponse> create(@RequestBody AvaliacaoFisicaCreateRequest request) {
        AvaliacaoFisicaResponse novaAvaliacao = service.create(request);
        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AvaliacaoFisicaResponse> update(@PathVariable("id") Long id,
                                                @Valid
                                                @RequestBody AvaliacaoFisicaUpdateRequest request) {
        AvaliacaoFisicaResponse avaliacaoAtualizada = service.update(id, request);
        return new ResponseEntity<>(avaliacaoAtualizada, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
