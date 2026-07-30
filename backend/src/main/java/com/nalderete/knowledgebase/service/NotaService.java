package com.nalderete.knowledgebase.service;

import com.nalderete.knowledgebase.model.Nota;
import java.util.List;

public interface NotaService {
    Nota createNota(Nota nota);
    Nota getNotaById(Long id);
    Nota updateNota(Long id, Nota nota);
    void deleteNota(Long id);
    List<Nota> getAllNotas();
    void deleteAllNotas();
}