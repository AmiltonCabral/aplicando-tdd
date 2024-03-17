package edu.ufcg.functionalTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.ufcg.processadordeboletos.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TabelaDeDecisaoTest {
    ProcessadorBoletos processador = new ProcessadorBoletos();
    List<Boleto> boletos = new ArrayList<>();
    Fatura fatura;

    @BeforeEach
    void init() {
        boletos.clear();
    }

    @Test
    void testCaso1() {
        this.fatura = new Fatura("Amilton Cabral", 50, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 50));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso2() {
        this.fatura = new Fatura("Amilton Cabral", 50, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 100));
        processador.processarBoletos(boletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    void testCaso3() {
        this.fatura = new Fatura("Amilton Cabral", 100, LocalDate.now());
        boletos.add(new Boleto("000.1", LocalDate.now(), 50));
        processador.processarBoletos(boletos, fatura);
        assertFalse(fatura.isPaga());
    }
}
