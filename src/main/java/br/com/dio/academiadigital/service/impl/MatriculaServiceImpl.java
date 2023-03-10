package br.com.dio.academiadigital.service.impl;

import br.com.dio.academiadigital.exception.AlunoNotFoundException;
import br.com.dio.academiadigital.exception.MatriculaNotFoundException;
import br.com.dio.academiadigital.model.dto.MatriculaRequest;
import br.com.dio.academiadigital.model.dto.MatriculaResponse;
import br.com.dio.academiadigital.model.entity.Aluno;
import br.com.dio.academiadigital.model.entity.Matricula;
import br.com.dio.academiadigital.repository.AlunoRepository;
import br.com.dio.academiadigital.repository.MatriculaRepository;
import br.com.dio.academiadigital.service.MatriculaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaServiceImpl implements MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public MatriculaResponse create(MatriculaRequest request) {
        Matricula matricula = new Matricula();
        Optional<Aluno> aluno = alunoRepository.findById(request.getAlunoId());
        if (aluno.isEmpty()) { throw new AlunoNotFoundException(); }
        Aluno alunoEncontrado = aluno.get();
        matricula.setAluno(alunoEncontrado);
        var matriculaSalva = matriculaRepository.save(matricula);
        return new MatriculaResponse(matriculaSalva.getDataMatricula(), matriculaSalva.getAluno());
    }

    @Override
    public MatriculaResponse getById(Long id) {
        var resultado = matriculaRepository.findById(id);
        if (resultado.isEmpty()) { throw new MatriculaNotFoundException(); }
        Matricula matricula = resultado.get();
        return new MatriculaResponse(
                matricula.getDataMatricula(),
                matricula.getAluno()
        );
    }

    @Override
    public List<MatriculaResponse> getAll(String bairro) {
        if (bairro == null) {
            var matriculas = matriculaRepository.findAll();
        }
        var matriculas = matriculaRepository.findByAlunoBairro(bairro);

        return matriculas.stream().map(matricula -> new MatriculaResponse(
                matricula.getDataMatricula(),
                matricula.getAluno()
        )).collect(Collectors.toList());
    }
}
