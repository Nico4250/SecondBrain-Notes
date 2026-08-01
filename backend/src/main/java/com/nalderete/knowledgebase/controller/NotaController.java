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

import com.nalderete.knowledgebase.model.Nota;
import com.nalderete.knowledgebase.service.NotaService;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping
    public List<Nota> getAllNotas() {
        return notaService.getAllNotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaById(@PathVariable Long id) {
        return ResponseEntity.ok(notaService.getNotaById(id));
    }

    @PostMapping
    public ResponseEntity<Nota> createNota(@RequestBody Nota nota) {
        Nota creada = notaService.createNota(nota);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable Long id, @RequestBody Nota nota) {
        return ResponseEntity.ok(notaService.updateNota(id, nota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaService.deleteNota(id);
        return ResponseEntity.noContent().build();
    }
}
