package br.com.dio.academiadigital.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avaliacao_fisica")
public class AvaliacaoFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private LocalDateTime dataAvaliacao = LocalDateTime.now();

    private double peso;

    private double altura;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
