package com.nalderete.knowledgebase.controller.DTO.request;

import com.nalderete.knowledgebase.model.Nota;

public record CreateNotaRequest(String titulo, String contenido) {
    public Nota aModelo() {
        return new Nota(null, this.titulo, this.contenido);
    }
}
