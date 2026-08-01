package com.nalderete.knowledgebase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalderete.knowledgebase.DAO.NotaDAO;
import com.nalderete.knowledgebase.model.Nota;
import com.nalderete.knowledgebase.model.exception.NotaNoEncontradaException;
import com.nalderete.knowledgebase.service.NotaService;

@Service
public class NotaServiceImpl implements NotaService {
    @Autowired
    private NotaDAO notaDAO;

    @Override
    public Nota createNota(Nota nota) { // C
        return notaDAO.save(nota);
    }

    @Override
    public Nota getNotaById(Long id) {
        return notaDAO.findById(id)
                .orElseThrow(() -> new NotaNoEncontradaException(id));
    }

    @Override
    public Nota updateNota(Long id, Nota nota) {
        Nota existente = getNotaById(id);
        existente.setTitulo(nota.getTitulo());
        existente.setContenido(nota.getContenido());
        return notaDAO.save(existente);
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
