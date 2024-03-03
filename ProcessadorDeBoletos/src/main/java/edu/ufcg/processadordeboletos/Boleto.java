package edu.ufcg.processadordeboletos;

import java.time.LocalDate;

public class Boleto {
    private String codigo;
    private LocalDate data;
    private double valorPago;

    public Boleto(String codigo, LocalDate data, double valorPago) {
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
    }

    public double getValorPago() {
        return this.valorPago;
    }

    public LocalDate getDataPagamento() {
        return this.data;
    }
}
