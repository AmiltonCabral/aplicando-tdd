package edu.ufcg.processadordeboletos;

import java.util.ArrayList;
import java.util.List;

public class ProcessadorBoletos {
    public List<Pagamento> processarBoletos(List<Boleto> boletos, Fatura fatura) {
        List<Pagamento> pagamentos = new ArrayList<Pagamento>();
        double totalPago = 0;

        for (Boleto boleto : boletos) {
            totalPago += boleto.getValorPago();
            pagamentos.add(new Pagamento("BOLETO", boleto.getDataPagamento(), boleto.getValorPago()));
        }

        if (totalPago >= fatura.getValor()) {
            fatura.setPaga();
        }

        return pagamentos;
    }
}
