package br.com.dio.academiadigital.exception;

public class AvaliacaoFisicaNotFoundException extends RuntimeException {
    public AvaliacaoFisicaNotFoundException() {
        super ("Avaliação Física não encontrada.");
    }
}
