package edu.ufcg.processadordeboletos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class ProcessadorBoletosTest {
    @Test
    void testProcessamentoBoletoFaturaPaga() {
        Fatura fatura = new Fatura("Alice Medeiros", 1500.00, LocalDate.now());

        List<Boleto> boletos = new ArrayList<Boleto>();
        boletos.add(new Boleto("000.1", LocalDate.now(), 500.00));
        boletos.add(new Boleto("000.2", LocalDate.now(), 400.00));
        boletos.add(new Boleto("000.3", LocalDate.now(), 600.00));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(boletos, fatura);

        assertTrue(fatura.isPaga());
    }

    @Test
    void testProcessamentoBoletosFaturaNaoPaga() {
        Fatura fatura = new Fatura("Alice Medeiros", 1500.00, LocalDate.now());

        List<Boleto> boletos = new ArrayList<Boleto>();
        boletos.add(new Boleto("001", LocalDate.now(), 500.00));
        boletos.add(new Boleto("002", LocalDate.now(), 400.00));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processarBoletos(boletos, fatura);

        assertFalse(fatura.isPaga());
    }

    @Test
    void testProcessamentoBoletosPagamentoCriado() {
        Fatura fatura = new Fatura("Cliente Teste", 1500.00, LocalDate.now());

        List<Boleto> boletos = new ArrayList<Boleto>();
        boletos.add(new Boleto("001", LocalDate.now(), 500.00));
        boletos.add(new Boleto("002", LocalDate.now(), 400.00));
        boletos.add(new Boleto("003", LocalDate.now(), 600.00));

        ProcessadorBoletos processador = new ProcessadorBoletos();
        List<Pagamento> pagamentos = processador.processarBoletos(boletos, fatura);

        assertEquals(3, pagamentos.size());
        assertEquals(500, pagamentos.get(0).getValorPago(), 0.01);
        assertEquals(400, pagamentos.get(1).getValorPago(), 0.01);
        assertEquals(600, pagamentos.get(2).getValorPago(), 0.01);
    }

    @Test
    void testProcessamentoBoletosListaVazia() {
        Fatura fatura = new Fatura("Cliente Teste", 1500.00, LocalDate.now());

        List<Boleto> boletos = new ArrayList<Boleto>();

        ProcessadorBoletos processador = new ProcessadorBoletos();
        List<Pagamento> pagamentos = processador.processarBoletos(boletos, fatura);

        assertEquals(0, pagamentos.size());

        assertFalse(fatura.isPaga());
    }

    void testProcessamentoBoletosListaVaziaGratis() {
        Fatura fatura = new Fatura("Cliente Teste", 0.00, LocalDate.now());

        List<Boleto> boletos = new ArrayList<Boleto>();

        ProcessadorBoletos processador = new ProcessadorBoletos();
        List<Pagamento> pagamentos = processador.processarBoletos(boletos, fatura);

        assertEquals(0, pagamentos.size());

        assertTrue(fatura.isPaga());
    }
}
