package com.nalderete.knowledgebase.service;

import java.util.List;

import com.nalderete.knowledgebase.model.Carpeta;

public interface CarpetaService {
    Carpeta createCarpeta(Carpeta carpeta);
    Carpeta getCarpetaById(Long id);
    Carpeta updateCarpeta(Long id, Carpeta carpeta);
    void deleteCarpeta(Long id);
    List<Carpeta> getAllCarpetas();
    void deleteAllCarpetas();
}
