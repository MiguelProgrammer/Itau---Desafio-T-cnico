package br.com.itau.geradornotafiscal.core.usecase.registro;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import org.springframework.stereotype.Component;

@Component
public class RegistroService {

    public void registrarNotaFiscal(NotaFiscal notaFiscal) {

        try {
            //Simula o registro da nota fiscal
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
