package com.nalderete.knowledgebase.controller.DTO.response;

import java.time.Instant;

import com.nalderete.knowledgebase.model.Nota;

public record NotaResponse(
        Long id,
        String titulo,
        String contenido,
        Long carpetaId,
        String carpetaNombre,
        Instant fechaCreacion,
        Instant fechaModificacion
) {
    public static NotaResponse desdeModelo(Nota nota) {
        Long carpetaId = nota.getCarpeta() != null ? nota.getCarpeta().getId() : null;
        String carpetaNombre = nota.getCarpeta() != null ? nota.getCarpeta().getNombre() : null;

        return new NotaResponse(
                nota.getId(),
                nota.getTitulo(),
                nota.getContenido(),
                carpetaId,
                carpetaNombre,
                nota.getFechaCreacion(),
                nota.getFechaModificacion()
        );
    }
}   