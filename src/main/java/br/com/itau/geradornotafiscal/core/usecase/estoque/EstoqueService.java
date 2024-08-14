package br.com.itau.geradornotafiscal.core.usecase.estoque;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import org.springframework.stereotype.Component;

@Component
public class EstoqueService {

    public void enviarNotaFiscalParaBaixaEstoque(NotaFiscal notaFiscal) {

        try {
            //Simula envio de nota fiscal para baixa de estoque
            Thread.sleep(380);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
