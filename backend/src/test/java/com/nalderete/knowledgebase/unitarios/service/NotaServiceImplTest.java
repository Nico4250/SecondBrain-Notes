package com.nalderete.knowledgebase.unitarios.service;

import com.nalderete.knowledgebase.DAO.NotaDAO;
import com.nalderete.knowledgebase.model.Nota;
import com.nalderete.knowledgebase.model.exception.NotaNoEncontradaException;
import com.nalderete.knowledgebase.service.impl.NotaServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotaServiceImplTest {

    @Mock
    private NotaDAO notaDAO;

    @InjectMocks
    private NotaServiceImpl notaService;

    private Nota nota;

    @BeforeEach
    void setUp() {
        nota = new Nota(1L, "Nota de prueba", "Contenido de prueba", null);
    }

    @Test
    void createNotaGuardaYDevuelveLaNota() {
        when(notaDAO.save(nota)).thenReturn(nota);
        Nota resultado = notaService.createNota(nota);
        assertEquals("Nota de prueba", resultado.getTitulo());
        verify(notaDAO, times(1)).save(nota);
    }

    @Test
    void existeUnaNota() {
        when(notaDAO.findById(1L)).thenReturn(Optional.of(nota));
        Nota resultado = notaService.getNotaById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void notaQueNoExisteLanzaExcepcion() {
        when(notaDAO.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotaNoEncontradaException.class, () -> notaService.getNotaById(99L));
    }

    @Test
    void notaSeActualizaCorrectamente() {
        when(notaDAO.findById(1L)).thenReturn(Optional.of(nota));
        when(notaDAO.save(any(Nota.class))).thenAnswer(inv -> inv.getArgument(0));

        Nota nuevosDatos = new Nota(null, "Modificada", "Nuevo contenido", null);
        Nota resultado = notaService.updateNota(1L, nuevosDatos);

        assertEquals("Modificada", resultado.getTitulo());
        assertEquals("Nuevo contenido", resultado.getContenido());
    }

    @Test
    void actualizarNotaQueNoExistelanzaExcepcion() {
        when(notaDAO.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotaNoEncontradaException.class,
                () -> notaService.updateNota(99L, new Nota(null, "X", "Y", null)));
    }

    @Test
    void deleteNotaLllamaAlDAO() {
        notaService.deleteNota(1L);
        verify(notaDAO, times(1)).deleteById(1L);
    }
}