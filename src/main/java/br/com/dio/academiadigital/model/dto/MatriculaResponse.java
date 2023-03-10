package br.com.dio.academiadigital.model.dto;

import br.com.dio.academiadigital.model.entity.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MatriculaResponse {
    private LocalDateTime dataMatricula;
    private Aluno aluno;
}
