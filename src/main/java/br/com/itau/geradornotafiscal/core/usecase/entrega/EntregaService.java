package br.com.itau.geradornotafiscal.core.usecase.entrega;

import br.com.itau.geradornotafiscal.adapter.outbound.EntregaIntegrationPort;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import org.springframework.stereotype.Component;

@Component
public class EntregaService {

    public void agendarEntrega(NotaFiscal notaFiscal) {
        try {
            //Simula o agendamento da entrega
            Thread.sleep(150);
            Thread thread = new Thread(new EntregaIntegrationPort(notaFiscal));
            thread.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
