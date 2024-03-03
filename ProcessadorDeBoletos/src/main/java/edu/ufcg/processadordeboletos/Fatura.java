package edu.ufcg.processadordeboletos;

import java.time.LocalDate;

public class Fatura {
    private String nomeCliente;
    private double valorTotal;
    private LocalDate data;
    private boolean isPaga;

    public Fatura(String nomeCliente, double valorTotal, LocalDate data) {
        this.nomeCliente = nomeCliente;
        this.valorTotal = valorTotal;
        this.data = data;
        this.isPaga = false;
    }

    public double getValor() {
        return this.valorTotal;
    }

    public boolean isPaga() {
        return this.isPaga;
    }

    public void setPaga() {
        this.isPaga = true;
    }
}
