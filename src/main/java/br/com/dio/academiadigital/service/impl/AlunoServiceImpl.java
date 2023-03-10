package br.com.dio.academiadigital.service.impl;

import br.com.dio.academiadigital.exception.AlunoNotFoundException;
import br.com.dio.academiadigital.model.dto.AlunoCreateRequest;
import br.com.dio.academiadigital.model.dto.AlunoResponse;
import br.com.dio.academiadigital.model.dto.AlunoUpdateRequest;
import br.com.dio.academiadigital.model.entity.Aluno;
import br.com.dio.academiadigital.model.entity.AvaliacaoFisica;
import br.com.dio.academiadigital.repository.AlunoRepository;
import br.com.dio.academiadigital.service.AlunoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {
    private final AlunoRepository repository;

    public AlunoServiceImpl(AlunoRepository repository) {
        this.repository = repository;
    }

    @Override
    public AlunoResponse create(AlunoCreateRequest request) {
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(request.getNome());
        novoAluno.setCpf(request.getCpf());
        novoAluno.setBairro(request.getBairro());
        novoAluno.setDataNascimento(request.getDataNascimento());

        var alunoSalvo = repository.save(novoAluno);

        return new AlunoResponse(
                alunoSalvo.getId(),
                alunoSalvo.getNome(),
                alunoSalvo.getCpf(),
                alunoSalvo.getBairro(),
                alunoSalvo.getDataNascimento(),
                alunoSalvo.getAvaliacoes()
        );
    }

    @Override
    public AlunoResponse getById(Long id) {
        var resultado = repository.findById(id);
        if (resultado.isEmpty()) { throw new AlunoNotFoundException(); }
        Aluno aluno = resultado.get();

        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getBairro(),
                aluno.getDataNascimento(),
                aluno.getAvaliacoes()
        );
    }

    @Override
    public List<AlunoResponse> getAll() {
        var resultado = repository.findAll();

        return resultado.stream().map(aluno -> new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getBairro(),
                aluno.getDataNascimento(),
                aluno.getAvaliacoes()
        )).collect(Collectors.toList());
    }

    @Override
    public AlunoResponse update(Long id, AlunoUpdateRequest request) {
        var resultado = repository.findById(id);
        if (resultado.isEmpty()) { throw new AlunoNotFoundException(); }
        Aluno aluno = resultado.get();
        aluno.setNome(request.getNome());
        aluno.setBairro(request.getBairro());
        aluno.setDataNascimento(request.getDataNascimento());

        var alunoAtualizado = repository.save(aluno);

        return new AlunoResponse(
                alunoAtualizado.getId(),
                alunoAtualizado.getNome(),
                alunoAtualizado.getCpf(),
                alunoAtualizado.getBairro(),
                alunoAtualizado.getDataNascimento(),
                alunoAtualizado.getAvaliacoes()
        );
    }

    @Override
    public void delete(Long id) {
        var resultado = repository.findById(id);
        if (resultado.isEmpty()) { throw new AlunoNotFoundException(); }
        Aluno aluno = resultado.get();
        repository.delete(aluno);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
        Aluno aluno = repository.findById(id).get();
        return aluno.getAvaliacoes();
    }
}
