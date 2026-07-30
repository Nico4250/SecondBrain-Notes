package com.nalderete.knowledgebase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.nalderete.knowledgebase.DAO.NotaDAO;
import com.nalderete.knowledgebase.model.Nota;
import com.nalderete.knowledgebase.service.NotaService;

import org.springframework.stereotype.Service;

@Service
public class NotaServiceImpl implements NotaService {
    @Autowired
    private NotaDAO notaDAO;

    @Override
    public Nota createNota(Nota nota) { // C
        return notaDAO.save(nota);
    }

    public Nota getNotaById(Long id) {
        return notaDAO.findById(id).orElse(null); // R
    }

    public Nota updateNota(Long id, Nota nota) { // U
        Nota existingNota = notaDAO.findById(id).orElse(null);
        if (existingNota != null) {
            existingNota.setTitulo(nota.getTitulo());
            existingNota.setContenido(nota.getContenido());
            return notaDAO.save(existingNota);
        }
        return null;
    }

    public void deleteNota(Long id) { // D
        notaDAO.deleteById(id);
    }

    public List<Nota> getAllNotas() { // R
        return notaDAO.findAll();
    }

    public void deleteAllNotas() { // D (util para tests)
        notaDAO.deleteAll();
    }

}
