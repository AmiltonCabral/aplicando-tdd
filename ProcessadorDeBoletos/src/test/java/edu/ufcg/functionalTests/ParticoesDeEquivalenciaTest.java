package edu.ufcg.functionalTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.ufcg.processadordeboletos.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParticoesDeEquivalenciaTest {
    ProcessadorBoletos processador = new ProcessadorBoletos();
    List<Boleto> boletos = new ArrayList<>();
    Fatura fatura;

    @BeforeEach
    void init() {
        boletos.clear();
    }

    @Test
    void testCaso1() {
        assertThrows(Throwable.class, () -> {
            this.fatura = new Fatura("Amilton Cabral", -10, LocalDate.now());
            boletos.add(new Boleto("000.1", LocalDate.now(), -10));
            processador.processarBoletos(boletos, fatura);
        });
    }

    @Test
    void testCaso2() {
        assertThrows(Throwable.class, () -> {
            this.fatura = new Fatura("Amilton Cabral", -10, LocalDate.now());
            boletos.add(new Boleto("000.1", LocalDate.now(), 0.00));
            processador.processarBoletos(boletos, fatura);
        });
    }

    @Test
    void testCaso3() {
        assertThrows(Throwable.class, () -> {
            this.fatura = new Fatura("Amilton Cabral", -10, LocalDate.now());
            boletos.add(new Boleto("000.1", LocalDate.now(), 10));
            processador.processarBoletos(boletos, fatura);
        });
    }

    @Test
    void testCaso4() {
        assertThrows(Throwable.class, () -> {
            this.fatura = new Fatura("Amilton Cabral", 0.00, LocalDate.now());
            boletos.add(new Boleto("000.1", LocalDate.now(), -10));
            processador.processarBoletos(boletos, fatura);
        });
    }

    @Test
    void testCaso5() {
        this.fatura = new Fatura("Amilton Cabral", 0.00, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.00));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso6() {
        this.fatura = new Fatura("Amilton Cabral", 0.00, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 10));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso7() {
        assertThrows(Throwable.class, () -> {
            this.fatura = new Fatura("Amilton Cabral", 10, LocalDate.now());
            boletos.add(new Boleto("000.1", LocalDate.now(), -10));
            processador.processarBoletos(boletos, fatura);
        });
    }

    @Test
    void testCaso8() {
        this.fatura = new Fatura("Amilton Cabral", 10, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }

    @Test
    void testCaso9() {
        this.fatura = new Fatura("Amilton Cabral", 10, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 10));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }
}
