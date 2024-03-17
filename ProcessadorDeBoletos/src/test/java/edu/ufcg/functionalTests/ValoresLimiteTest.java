package edu.ufcg.functionalTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.ufcg.processadordeboletos.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValoresLimiteTest {
    ProcessadorBoletos processador = new ProcessadorBoletos();
    List<Boleto> boletos = new ArrayList<>();
    Fatura fatura;

    @BeforeEach
    void init() {
        boletos.clear();
    }

    @Test
    void testCaso1() {
        this.fatura = new Fatura("Amilton Cabral", 0.00, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.00));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso2() {
        this.fatura = new Fatura("Amilton Cabral", 0.00, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.01));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso3() {
        this.fatura = new Fatura("Amilton Cabral", 0.01, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.00));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }

    @Test
    void testCaso4() {
        this.fatura = new Fatura("Amilton Cabral", 0.01, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.01));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso5() {
        this.fatura = new Fatura("Amilton Cabral", 0.00, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 100000.00));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso6() {
        this.fatura = new Fatura("Amilton Cabral", 0.01, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 100000.00));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso7() {
        this.fatura = new Fatura("Amilton Cabral", 0.00, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 99999.99));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso8() {
        this.fatura = new Fatura("Amilton Cabral", 0.00, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 99999.99));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso9() {
        this.fatura = new Fatura("Amilton Cabral", 100000, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.00));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }

    @Test
    void testCaso10() {
        this.fatura = new Fatura("Amilton Cabral", 99999.99, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.00));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }

    @Test
    void testCaso11() {
        this.fatura = new Fatura("Amilton Cabral", 100000, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.01));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }

    @Test
    void testCaso12() {
        this.fatura = new Fatura("Amilton Cabral", 99999.99, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 0.01));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }

    @Test
    void testCaso13() {
        this.fatura = new Fatura("Amilton Cabral", 100000, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 100000));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso14() {
        this.fatura = new Fatura("Amilton Cabral", 99999.99, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 100000));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso15() {
        this.fatura = new Fatura("Amilton Cabral", 100000, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 99999.99));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }

    @Test
    void testCaso16() {
        this.fatura = new Fatura("Amilton Cabral", 99999.99, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 99999.99));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso17() {
        assertThrows(Throwable.class, () -> {
            this.fatura = new Fatura("Amilton Cabral", 1234, LocalDate.now());
            boletos.add(new Boleto("000.1", LocalDate.now(), -0.01));
            processador.processarBoletos(boletos, fatura);
        });
    }

    @Test
    void testCaso18() {
        this.fatura = new Fatura("Amilton Cabral", 1234, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 100001));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso19() {
        assertThrows(Throwable.class, () -> {
            this.fatura = new Fatura("Amilton Cabral", -0.01, LocalDate.now());
            boletos.add(new Boleto("000.1", LocalDate.now(), 1234));
            processador.processarBoletos(boletos, fatura);
        });
    }

    @Test
    void testCaso20() {
        this.fatura = new Fatura("Amilton Cabral", 100001, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 1234));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }
}
