package br.com.dio.academiadigital.service;

import br.com.dio.academiadigital.model.dto.AlunoCreateRequest;
import br.com.dio.academiadigital.model.dto.AlunoResponse;
import br.com.dio.academiadigital.model.dto.AlunoUpdateRequest;
import br.com.dio.academiadigital.model.entity.AvaliacaoFisica;

import java.util.List;

public interface AlunoService {
    /**
     * Cria novo aluno e salva no banco de dados.
     * @param request requisição do tipo POST com os dados referentes ao Aluno.
     * @return Aluno recém-criado.
     */
    AlunoResponse create(AlunoCreateRequest request);

    /**
     * Recupera um aluno específico pelo seu id.
     * @param id
     * @return Aluno response.
     */
    AlunoResponse getById(Long id);

    /**
     * Recupera todos os alunos cadastrados.
     * @return lista de Alunos.
     */
    List<AlunoResponse> getAll();

    /**
     * Atualiza o aluno.
     * @param id id do Aluno que será atualizado.
     * @param request requisição do tipo PATCH com os dados referentes ao Aluno.
     * @return response de aluno atualizado.
     */
    AlunoResponse update(Long id, AlunoUpdateRequest request);

    /**
     * Deleta um Aluno específico.
     * @param id id do Aluno que será removido.
     *
     */
    void delete(Long id);

    /**
     * Recupera lista de Avaliações Fisicas do Aluno.
     * @param id id do aluno que será recuperada a lista de avaliações
     * @return uma lista com todas as avaliações do aluno
     */
    List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id);
}
