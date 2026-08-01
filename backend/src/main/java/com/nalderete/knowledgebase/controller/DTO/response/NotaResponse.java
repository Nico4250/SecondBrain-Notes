package com.nalderete.knowledgebase.Controller.DTO.response;

public record NotaResponse() {

    public static NotaResponse desdeModelo(Nota nota) {
        return new NotaResponse(
                nota.getId(),
                nota.getTitulo(),
                nota.getContenido()
        );
    }

}
