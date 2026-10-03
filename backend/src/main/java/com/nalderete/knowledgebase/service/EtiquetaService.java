package com.nalderete.knowledgebase.service;

import com.nalderete.knowledgebase.model.Etiqueta;
import java.util.List;

public interface EtiquetaService {
    Etiqueta createEtiqueta(Etiqueta etiqueta);
    Etiqueta getEtiquetaById(Long id);
    Etiqueta updateEtiqueta(Long id, Etiqueta etiqueta);
    void deleteEtiqueta(Long id);
    List<Etiqueta> getAllEtiquetas();
    void deleteAllEtiquetas();
}