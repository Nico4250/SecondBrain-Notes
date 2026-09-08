package com.nalderete.knowledgebase.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nalderete.knowledgebase.model.Carpeta;

@Repository
public interface EtiquetaDAO extends JpaRepository<Etiqueta, Long>{

}
