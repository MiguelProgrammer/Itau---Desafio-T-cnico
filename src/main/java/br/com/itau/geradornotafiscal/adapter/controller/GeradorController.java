package br.com.itau.geradornotafiscal.adapter.controller;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.impl.GeradorNotaFiscalServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GeradorController {

    private GeradorNotaFiscalServiceImpl notaFiscalService;

    public GeradorController(GeradorNotaFiscalServiceImpl notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

    public NotaFiscal gerarNota(Pedido pedido) {
        return notaFiscalService.gerarNotaFiscal(pedido);
    }
}
