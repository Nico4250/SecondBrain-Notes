package com.nalderete.knowledgebase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalderete.knowledgebase.DAO.CarpetaDAO;
import com.nalderete.knowledgebase.model.Carpeta;
import com.nalderete.knowledgebase.model.exception.CarpetaNoEncontradaException;
import com.nalderete.knowledgebase.service.CarpetaService;

@Service
public class CarpetaServiceImpl implements CarpetaService{

    @Autowired
    private CarpetaDAO carpetaDAO;

    @Override
    public Carpeta createCarpeta(Carpeta carpeta) {
        return carpetaDAO.save(carpeta);
    }

    @Override
    public Carpeta getCarpetaById(Long id) {
        return carpetaDAO.findById(id)
                .orElseThrow(() -> new CarpetaNoEncontradaException(id));
    }

    @Override
    public Carpeta updateCarpeta(Long id, Carpeta carpeta) {
        Carpeta existente = getCarpetaById(id);
        existente.setNombre(carpeta.getNombre());
        existente.setDescripcion(carpeta.getDescripcion());
        return carpetaDAO.save(existente);
    }

    @Override
    public void deleteCarpeta(Long id) {
        carpetaDAO.deleteById(id);
    }

    @Override
    public List<Carpeta> getAllCarpetas() {
        return carpetaDAO.findAll();    
    }

    @Override
    public void deleteAllCarpetas() {
        carpetaDAO.deleteAll();    
    }
}
