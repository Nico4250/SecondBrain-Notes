package com.nalderete.knowledgebase.controller.dto.response;

import com.nalderete.knowledgebase.model.Nota;

import java.time.Instant;

public record NotaResponse(Long id, String titulo, String contenido, Instant fechaCreacion, Instant fechaModificacion) {
    public static NotaResponse desdeModelo(Nota nota) {
        return new NotaResponse(
                nota.getId(),
                nota.getTitulo(),
                nota.getContenido(),
                nota.getFechaCreacion(),
                nota.getFechaModificacion()
        );
    }
}