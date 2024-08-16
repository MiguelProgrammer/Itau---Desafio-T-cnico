package br.com.itau.geradornotafiscal.adapter.controller;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.TipoNotaFiscal;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.impl.GeradorNotaFiscalServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GeradorController {

    private TipoNotaFiscal tipoNotaFiscal;
    private GeradorNotaFiscalServiceImpl notaFiscalService;

    public GeradorController(TipoNotaFiscal tipoNotaFiscal,
                             GeradorNotaFiscalServiceImpl notaFiscalService) {
        this.tipoNotaFiscal = tipoNotaFiscal;
        this.notaFiscalService = notaFiscalService;
    }

    public NotaFiscal gerarNota(Pedido pedido) {
        NotaFiscal notaFiscal = notaFiscalService.gerarNotaFiscal(pedido);
        String mensagem = "Nota fiscal gerada com sucesso para o pedido: " + pedido.getIdPedido();
        System.out.println(mensagem);
        return notaFiscal;
    }
}
