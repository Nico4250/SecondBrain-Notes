package com.nalderete.knowledgebase.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nalderete.knowledgebase.model.Nota;

@Repository
public interface NotaDAO extends JpaRepository<Nota, Long> {
}   