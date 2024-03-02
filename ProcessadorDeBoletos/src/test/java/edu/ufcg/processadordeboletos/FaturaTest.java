package edu.ufcg.processadordeboletos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class FaturaTest {
    private static final double valorTotal = 1500.00;
    @Test
    void testValorTotalFatura() {
        Fatura fatura = new Fatura("Alice Medeiros", valorTotal, LocalDate.now());
        assertEquals(valorTotal, fatura.getValor());
    }

    @Test
    void testFaturaPaga() {
        Fatura fatura = new Fatura("Alice Medeiros", valorTotal, LocalDate.now());

        assertFalse(fatura.isPaga());

        fatura.setPaga();

        assertTrue(fatura.isPaga());
    }
}
