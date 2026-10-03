package com.nalderete.knowledgebase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nalderete.knowledgebase.model.exception.EtiquetaNoEncontradaException;
import com.nalderete.knowledgebase.DAO.EtiquetaDAO;
import com.nalderete.knowledgebase.model.Etiqueta;
import com.nalderete.knowledgebase.service.EtiquetaService;


@Service
public class EtiquetaServiceImpl implements EtiquetaService{

    @Autowired
    private EtiquetaDAO etiquetaDAO;

    @Override
    public Etiqueta createEtiqueta(Etiqueta etiqueta){
        return etiquetaDAO.save(etiqueta);
    }

    @Override
    public Etiqueta getEtiquetaById(Long id){
        return etiquetaDAO.findById(id)
                .orElseThrow(() -> new EtiquetaNoEncontradaException(id));
    }

    @Override
    public Etiqueta updateEtiqueta(Long id, Etiqueta etiqueta){
        Etiqueta existente = getEtiquetaById(id);
        existente.setNombre(etiqueta.getNombre());
        existente.setColor(etiqueta.getColor());
        return etiquetaDAO.save(existente);
    }
    
    @Override
    public void deleteEtiqueta(Long id){
        etiquetaDAO.deleteById(id);
    }

    @Override
    public List<Etiqueta> getAllEtiquetas(){
        return etiquetaDAO.findAll();
    }

    @Override
    public void deleteAllEtiquetas(){
        etiquetaDAO.deleteAll();
    }
}