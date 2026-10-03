package com.nalderete.knowledgebase.model.exception;

public class CarpetaNoEncontradaException extends RuntimeException {
    public CarpetaNoEncontradaException(Long id) {
        super("La carpeta con id " + id + " no fue encontrada.");
    }
}