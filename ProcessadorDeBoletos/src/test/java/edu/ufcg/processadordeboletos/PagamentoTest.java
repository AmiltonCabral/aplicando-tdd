package edu.ufcg.processadordeboletos;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PagamentoTest {
    private static final double valorPago = 400.00;

    @Test
    void testTipoPagamento() {

        Boleto boleto = new Boleto("000.000 0 000", LocalDate.now(), valorPago);

        Pagamento pagamento = new Pagamento("BOLETO", LocalDate.now(), boleto.getValorPago());

        assertEquals(pagamento.getTipoPagamento(), "BOLETO");
    }

    @Test
    void testValorPago() {
        Boleto boleto = new Boleto("000.000 0 000", LocalDate.now(), valorPago);

        Pagamento pagamento = new Pagamento("BOLETO", LocalDate.now(), boleto.getValorPago());

        assertEquals(pagamento.getValorPago(), valorPago);
    }
}
