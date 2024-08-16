package br.com.itau.geradornotafiscal.core.usecase.entrega;

import br.com.itau.geradornotafiscal.adapter.outbound.EntregaIntegrationPort;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class EntregaService {

    public void agendarEntrega(NotaFiscal notaFiscal) {
        try {
            //Simula o agendamento da entrega
            Thread.sleep(150);

            if(notaFiscal.getItens().size() > 5) {

                ItemNotaFiscal itemNotaFiscal1 = notaFiscal.getItens().get(0);
                ItemNotaFiscal itemNotaFiscal2 = notaFiscal.getItens().get(1);
                ItemNotaFiscal itemNotaFiscal3 = notaFiscal.getItens().get(2);

                NotaFiscal notaFiscal1 = new NotaFiscal();
                notaFiscal1.setItens(Arrays.asList(itemNotaFiscal1));

                NotaFiscal notaFiscal2 = new NotaFiscal();
                notaFiscal2.setItens(Arrays.asList(itemNotaFiscal2));

                NotaFiscal notaFiscal3 = new NotaFiscal();
                notaFiscal3.setItens(Arrays.asList(itemNotaFiscal3));

                Thread threadNota1 = new Thread(new EntregaIntegrationPort(notaFiscal1));
                threadNota1.start();

                Thread threadNota2 = new Thread(new EntregaIntegrationPort(notaFiscal2));
                threadNota2.start();

                Thread threadNota3 = new Thread(new EntregaIntegrationPort(notaFiscal3));
                threadNota3.start();

            } else {

                Thread thread = new Thread(new EntregaIntegrationPort(notaFiscal));
                thread.start();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
