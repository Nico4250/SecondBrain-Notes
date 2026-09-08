package com.nalderete.knowledgebase.model.exception;

public class EtiquetaNoEncontradaException extends RuntimeException {
    public EtiquetaNoEncontradaException(Long id) {
        super("La etiqueta con el id " + id + " no fue encontrada.");
    }
}