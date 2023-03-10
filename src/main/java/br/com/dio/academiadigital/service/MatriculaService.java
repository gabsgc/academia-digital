package br.com.dio.academiadigital.service;

import br.com.dio.academiadigital.model.dto.MatriculaRequest;
import br.com.dio.academiadigital.model.dto.MatriculaResponse;

import java.util.List;

public interface MatriculaService {
    /**
     * Cria uma Matrícula e salva no banco de dados.
     * @param request requisição do tipo POST com os dados referentes a Matrícula.
     * @return Matrícula recém-criada.
     */
    MatriculaResponse create(MatriculaRequest request);

    /**
     * Retorna uma Matrícula específica a partir do seu Id.
     * @param id id da Matrícula que será exibida.
     * @return Matrícula de acordo com o Id fornecido.
     */
    MatriculaResponse getById(Long id);

    /**
     * Retorna todas as Matrículas cadastradas.
     * @return lista com todas as Matrículas salvas no DB.
     */
    List<MatriculaResponse> getAll(String bairro);
}
