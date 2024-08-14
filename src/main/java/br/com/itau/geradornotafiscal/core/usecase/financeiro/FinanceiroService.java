package br.com.itau.geradornotafiscal.core.usecase.financeiro;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import org.springframework.stereotype.Component;

@Component
public class FinanceiroService {
    public void enviarNotaFiscalParaContasReceber(NotaFiscal notaFiscal) {

        try {
            //Simula o envio da nota fiscal para o contas a receber
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
