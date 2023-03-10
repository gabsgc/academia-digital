package br.com.dio.academiadigital.service.impl;

import br.com.dio.academiadigital.exception.AlunoNotFoundException;
import br.com.dio.academiadigital.exception.AvaliacaoFisicaNotFoundException;
import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaCreateRequest;
import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaResponse;
import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaUpdateRequest;
import br.com.dio.academiadigital.model.entity.Aluno;
import br.com.dio.academiadigital.model.entity.AvaliacaoFisica;
import br.com.dio.academiadigital.repository.AlunoRepository;
import br.com.dio.academiadigital.repository.AvaliacaoFisicaRepository;
import br.com.dio.academiadigital.service.AvaliacaoFisicaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoFisicaServiceImpl implements AvaliacaoFisicaService {
    private final AvaliacaoFisicaRepository avaliacaoFisicaRepository;
    private final AlunoRepository alunoRepository;

    public AvaliacaoFisicaServiceImpl(AvaliacaoFisicaRepository avaliacaoFisicaRepository, AlunoRepository alunoRepository) {
        this.avaliacaoFisicaRepository = avaliacaoFisicaRepository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public AvaliacaoFisicaResponse create(AvaliacaoFisicaCreateRequest request) {
        AvaliacaoFisica novaAvaliacao = new AvaliacaoFisica();
        Optional<Aluno> aluno = alunoRepository.findById(request.getAlunoId());
        if (aluno.isEmpty()) { throw new AlunoNotFoundException(); }
        Aluno alunoEncontrado = aluno.get();
        novaAvaliacao.setAluno(alunoEncontrado);
        novaAvaliacao.setAltura(request.getAltura());
        novaAvaliacao.setPeso(request.getPeso());

        var avaliacaoSalva = avaliacaoFisicaRepository.save(novaAvaliacao);
        return new AvaliacaoFisicaResponse(
                avaliacaoSalva.getDataAvaliacao(),
                avaliacaoSalva.getPeso(),
                avaliacaoSalva.getAltura(),
                avaliacaoSalva.getAluno()
        );
    }

    @Override
    public AvaliacaoFisicaResponse getById(Long id) {
        var resultado = avaliacaoFisicaRepository.findById(id);
        if (resultado.isEmpty()) { throw new AvaliacaoFisicaNotFoundException(); }
        AvaliacaoFisica avaliacaoFisica = resultado.get();

        return new AvaliacaoFisicaResponse(
                avaliacaoFisica.getDataAvaliacao(),
                avaliacaoFisica.getAltura(),
                avaliacaoFisica.getPeso(),
                avaliacaoFisica.getAluno()
        );
    }

    @Override
    public List<AvaliacaoFisicaResponse> getAll() {
        var avaliacoes = avaliacaoFisicaRepository.findAll();
        return avaliacoes.stream().map(avaliacaoFisica -> new AvaliacaoFisicaResponse(
                avaliacaoFisica.getDataAvaliacao(),
                avaliacaoFisica.getAltura(),
                avaliacaoFisica.getPeso(),
                avaliacaoFisica.getAluno()
        )).collect(Collectors.toList());
    }

    @Override
    public AvaliacaoFisicaResponse update(Long id, AvaliacaoFisicaUpdateRequest request) {
        var resultado = avaliacaoFisicaRepository.findById(id);
        if (resultado.isEmpty()) { throw new AvaliacaoFisicaNotFoundException(); }
        AvaliacaoFisica avaliacaoFisica = resultado.get();
        avaliacaoFisica.setAltura(request.getAltura());
        avaliacaoFisica.setPeso(request.getPeso());

        var avaliacaoAtualizada = avaliacaoFisicaRepository.save(avaliacaoFisica);
        return new AvaliacaoFisicaResponse(
                avaliacaoAtualizada.getDataAvaliacao(),
                avaliacaoFisica.getAltura(),
                avaliacaoFisica.getPeso(),
                avaliacaoFisica.getAluno()
        );
    }

    @Override
    public void delete(Long id) {
        var resultado = avaliacaoFisicaRepository.findById(id);
        if (resultado.isEmpty()) { throw new AvaliacaoFisicaNotFoundException(); }
        AvaliacaoFisica avaliacao = resultado.get();
        avaliacaoFisicaRepository.delete(avaliacao);
    }
}
