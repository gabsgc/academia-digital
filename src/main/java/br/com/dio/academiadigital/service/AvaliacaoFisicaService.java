package br.com.dio.academiadigital.service;

import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaCreateRequest;
import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaResponse;
import br.com.dio.academiadigital.model.dto.AvaliacaoFisicaUpdateRequest;

import java.util.List;

public interface AvaliacaoFisicaService {
    /**
     * Cria uma Avaliação Física e salva no banco de dados.
     * @param request requisição do tipo POST com os dados referentes à Avaliação Física no banco de dados.
     * @return - Avaliação Física recém-criada.
     */
    AvaliacaoFisicaResponse create(AvaliacaoFisicaCreateRequest request);

    /**
     * Recupera uma Avaliação Física específica pelo seu id.
     * @param id id da Avaliação Física que será exibida.
     * @return Avaliação Física de acordo com o Id fornecido.
     */
    AvaliacaoFisicaResponse getById(Long id);

    /**
     * Retorna todas as Avaliações Física cadastradas.
     * @return - lista com todas as Avaliações Física que estão salvas no DB.
     */
    List<AvaliacaoFisicaResponse> getAll();

    /**
     * Atualiza a avaliação física.
     * @param id id da Avaliação Física que será atualizada.
     * @param request requisição do tipo PATCH com os dados referentes à Avaliação
     * @return - Avaliação Física recém-atualizada.
     */
    AvaliacaoFisicaResponse update(Long id, AvaliacaoFisicaUpdateRequest request);

    /**
     * Deleta uma Avaliação Física específica.
     * @param id id da Avaliação Física que será removida.
     */
    void delete(Long id);
}
