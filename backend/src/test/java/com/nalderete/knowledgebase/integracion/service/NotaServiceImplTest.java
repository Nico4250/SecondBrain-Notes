package com.nalderete.knowledgebase.integracion.service;

import com.nalderete.knowledgebase.model.Nota;
import com.nalderete.knowledgebase.model.exception.NotaNoEncontradaException;
import com.nalderete.knowledgebase.service.NotaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotaServiceImplTest {

    @Autowired
    private NotaService notaService;

    private Nota notaCreada;
    private Nota notaCreada2;

    @BeforeEach
    void prepare() {
        notaService.deleteAllNotas();

        notaCreada = new Nota(null, "Nota base", "Contenido base", null);
        notaService.createNota(notaCreada);

        notaCreada2 = new Nota(null, "Segunda nota", "Otro contenido", null);
        notaService.createNota(notaCreada2);
    }

    @Test
    void crearNotaLeAsignaUnId() {
        Nota nueva = new Nota(null, "Otra nota", "Excel", null);
        notaService.createNota(nueva);

        assertNotNull(nueva.getId());
    }

    @Test
    void getAllNotasDevuelveTodasLasCreadas() {
        List<Nota> notas = notaService.getAllNotas();

        assertEquals(2, notas.size());
    }

    @Test
    void getNotaByIdDevuelveLaNotaCorrecta() {
        Nota encontrada = notaService.getNotaById(notaCreada.getId());

        assertNotNull(encontrada);
        assertEquals("Nota base", encontrada.getTitulo());
    }

    @Test
    void getNotaByIdConIdInexistenteLanzaExcepcion() {
        assertThrows(NotaNoEncontradaException.class, () -> notaService.getNotaById(999999L));
    }

    @Test
    void updateNotaActualizaTituloYContenido() {
        Nota actualizada = notaService.updateNota(
                notaCreada.getId(),
                new Nota(null, "Modificada", "Contenido modificado", null)
        );

        assertEquals("Modificada", actualizada.getTitulo());
        assertEquals("Contenido modificado", actualizada.getContenido());
    }

    @Test
    void unaNotaSeEliminaCorrectamente() {
        notaService.deleteNota(notaCreada.getId());

        assertThrows(NotaNoEncontradaException.class, () -> notaService.getNotaById(notaCreada.getId()));
    }

    @AfterEach
    void tearDown() {
        notaService.deleteAllNotas();
    }
}