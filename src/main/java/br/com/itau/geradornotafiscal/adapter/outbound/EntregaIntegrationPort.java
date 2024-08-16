package br.com.itau.geradornotafiscal.adapter.outbound;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;

public class EntregaIntegrationPort implements Runnable {

    private NotaFiscal notaFiscal;

    public EntregaIntegrationPort(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void criarAgendamentoEntrega(NotaFiscal notaFiscal) throws RuntimeException {

        try {
            //Simula o agendamento da entrega
            if (notaFiscal.getItens().size() > 5) {
                /**
                 * Aqui está o problema de performance do aplicacao para pedidos com mais de 5 itens
                 * Se voce chegou ate aqui basta remover esse valor de 5s para 'solucionar' o misterio
                 */
                Thread.sleep(5000);
            }
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        this.criarAgendamentoEntrega(this.notaFiscal);
    }
}
