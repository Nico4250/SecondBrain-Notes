package com.nalderete.knowledgebase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nalderete.knowledgebase.controller.DTO.request.ActualizarNotaRequest;
import com.nalderete.knowledgebase.controller.DTO.request.CreateNotaRequest;
import com.nalderete.knowledgebase.controller.DTO.response.NotaResponse;
import com.nalderete.knowledgebase.model.Carpeta;
import com.nalderete.knowledgebase.model.Nota;
import com.nalderete.knowledgebase.service.CarpetaService;
import com.nalderete.knowledgebase.service.NotaService;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @Autowired
    private CarpetaService carpetaService;

        @GetMapping
    public List<NotaResponse> getAllNotas() {
        return notaService.getAllNotas().stream()
                .map(NotaResponse::desdeModelo)
                .toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<NotaResponse> getNotaById(@PathVariable Long id) {
        return ResponseEntity.ok(NotaResponse.desdeModelo(notaService.getNotaById(id)));
    }

    @PostMapping
    public ResponseEntity<NotaResponse> createNota(@RequestBody CreateNotaRequest request) {
        Carpeta carpeta = resolverCarpeta(request.carpetaId());
        Nota creada = notaService.createNota(request.aModelo(carpeta));
        return ResponseEntity.status(201).body(NotaResponse.desdeModelo(creada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaResponse> updateNota(@PathVariable Long id, @RequestBody ActualizarNotaRequest request) {
        Carpeta carpeta = resolverCarpeta(request.carpetaId());
        Nota datos = new Nota(null, request.titulo(), request.contenido(), carpeta);
        Nota actualizada = notaService.updateNota(id, datos);
        return ResponseEntity.ok(NotaResponse.desdeModelo(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaService.deleteNota(id);
        return ResponseEntity.noContent().build();
    }

    private Carpeta resolverCarpeta(Long carpetaId) {
        return carpetaId != null ? carpetaService.getCarpetaById(carpetaId) : null;
    }
}
