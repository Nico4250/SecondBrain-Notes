package com.nalderete.knowledgebase.model.exception;

public class NotaNoEncontradaException extends RuntimeException {
    public NotaNoEncontradaException(Long id) {
        super("La nota con id " + id + " no fue encontrada.");
    }
}