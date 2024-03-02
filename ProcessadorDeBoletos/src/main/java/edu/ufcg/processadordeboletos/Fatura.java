package edu.ufcg.processadordeboletos;

import java.time.LocalDate;

public class Fatura {
    private String nomeCliente;
    private double valorTotal;
    private LocalDate data;

    public Fatura(String nomeCliente, double valorTotal, LocalDate data) {
        this.nomeCliente = nomeCliente;
        this.valorTotal = valorTotal;
        this.data = data;
    }

    public double getValor() {
        return this.valorTotal;
    }
}
