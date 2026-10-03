package com.nalderete.knowledgebase.controller.DTO.request;

import com.nalderete.knowledgebase.model.Carpeta;
import com.nalderete.knowledgebase.model.Nota;

public record CreateNotaRequest(String titulo, String contenido, Long carpetaId) {
    
    public Nota aModelo(Carpeta carpeta) {
        return new Nota(null, this.titulo, this.contenido, carpeta);
    }
}

