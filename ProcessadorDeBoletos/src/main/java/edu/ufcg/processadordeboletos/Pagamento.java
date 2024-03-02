package edu.ufcg.processadordeboletos;

import java.time.LocalDate;

public class Pagamento {
    private String tipoPagamento;
    private LocalDate data;
    private double valorPago;

    public Pagamento(String tipoPagamento, LocalDate data, double valorPago) {
        this.tipoPagamento = tipoPagamento;
        this.data = data;
        this.valorPago = valorPago;
    }

    public String getTipoPagamento() {
        return this.tipoPagamento;
    }
}
