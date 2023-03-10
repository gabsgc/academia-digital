package br.com.dio.academiadigital.model.dto;

import br.com.dio.academiadigital.model.entity.AvaliacaoFisica;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AlunoResponse {
    private Long id;
    private String  nome;
    private String cpf;
    private String bairro;
    private LocalDate dataNascimento;
    private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();
}
