package edu.ufcg.processadordeboletos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class BoletoTest {

    @Test
    void testValorPago() {
        final double valorPago = 400.00;
        Boleto boleto = new Boleto("000.000 0 000", LocalDate.now(), valorPago);
        assertEquals(valorPago, boleto.getValorPago());
    }

    @Test
    void testGetData() {
        LocalDate data = LocalDate.now();
        Boleto boleto = new Boleto("000.000 0 000", data, 100.00);
        assertEquals(data, boleto.getDataPagamento());
    }
}
