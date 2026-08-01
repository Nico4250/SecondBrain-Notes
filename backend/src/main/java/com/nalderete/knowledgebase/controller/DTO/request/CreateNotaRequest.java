package com.nalderete.knowledgebase.Controller.DTO.request;

public record CreateNotaRequest() {
    public Nota aModelo() {
        return new Nota(null, this.titulo, this.contenido);
    }
}
