package br.com.itau.geradornotafiscal.core.usecase.notafiscal.impl;

import br.com.itau.geradornotafiscal.core.domain.enums.Finalidade;
import br.com.itau.geradornotafiscal.core.domain.enums.Regiao;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;
import br.com.itau.geradornotafiscal.core.domain.pedido.destino.Destinatario;
import br.com.itau.geradornotafiscal.core.domain.pedido.destino.Endereco;
import br.com.itau.geradornotafiscal.core.usecase.calculadora.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.core.usecase.entrega.EntregaService;
import br.com.itau.geradornotafiscal.core.usecase.estoque.EstoqueService;
import br.com.itau.geradornotafiscal.core.usecase.financeiro.FinanceiroService;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.GeradorNotaFiscalService;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.TipoNotaFiscal;
import br.com.itau.geradornotafiscal.core.usecase.registro.RegistroService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GeradorNotaFiscalServiceImpl implements GeradorNotaFiscalService {

    private TipoNotaFiscal tipoNotaFiscal;
    private CalculadoraAliquotaProduto calculadoraAliquotaProduto;

    public GeradorNotaFiscalServiceImpl(TipoNotaFiscal tipoNotaFiscal) {
        this.tipoNotaFiscal = tipoNotaFiscal;
    }

    @Override
    public NotaFiscal gerarNotaFiscal(Pedido pedido) {
        double valorPercentual = this.fretePorRegiao(pedido, pedido.getDestinatario());
        List<ItemNotaFiscal> nf = new ArrayList<>();
        nf = tipoNotaFiscal.calcula(pedido, pedido.getDestinatario().getTipoPessoa());
        return preparaSaidaNotaFiscal(pedido, valorPercentual, nf);
    }

    private double fretePorRegiao(Pedido pedido, Destinatario destinatario) {

        Regiao regiao = destinatario.getEnderecos()
                .stream().filter(endereco -> endereco.getFinalidade() == Finalidade.ENTREGA ||
                        endereco.getFinalidade() == Finalidade.COBRANCA_ENTREGA)
                .map(Endereco::getRegiao)
                .findFirst().orElse(null);

        return calculaFrete(pedido, regiao);
    }

    private static double calculaFrete(Pedido pedido, Regiao regiao) {
        double valorFrete = pedido.getValorFrete();
        double valorFreteComPercentual = 0;

        if (regiao == Regiao.NORTE) {
            valorFreteComPercentual = valorFrete * 1.08;
        }

        if (regiao == Regiao.NORDESTE) {
            valorFreteComPercentual = valorFrete * 1.085;
        }

        if (regiao == Regiao.CENTRO_OESTE) {
            valorFreteComPercentual = valorFrete * 1.07;
        }

        if (regiao == Regiao.SUDESTE) {
            valorFreteComPercentual = valorFrete * 1.048;
        }

        if (regiao == Regiao.SUL) {
            valorFreteComPercentual = valorFrete * 1.06;
        }

        return valorFreteComPercentual;
    }

    private NotaFiscal preparaSaidaNotaFiscal(Pedido pedido, double valorFreteComPercentual,
                                              List<ItemNotaFiscal> itemNotaFiscalList) {

        pedido = mapeiaPedido(pedido, itemNotaFiscalList);

        NotaFiscal notaFiscal = NotaFiscal.builder()
                .idNotaFiscal(UUID.randomUUID().toString())
                .data(LocalDateTime.now()).valorTotalItens(pedido.getValorTotalItens())
                .valorFrete(valorFreteComPercentual).itens(itemNotaFiscalList)
                .destinatario(pedido.getDestinatario())
                .build();

        new EstoqueService().enviarNotaFiscalParaBaixaEstoque(notaFiscal);
        new RegistroService().registrarNotaFiscal(notaFiscal);
        new EntregaService().agendarEntrega(notaFiscal);
        new FinanceiroService().enviarNotaFiscalParaContasReceber(notaFiscal);
        return notaFiscal;
    }

    private Pedido mapeiaPedido(Pedido pedido, List<ItemNotaFiscal> itemNotaFiscalList) {

        double valorItensQtd = pedido.getItens().stream().mapToDouble(item -> {
            return item.getValorUnitario() * item.getQuantidade();
        }).sum();

        return new Pedido().builder().
                idPedido(pedido.getIdPedido()).
                data(pedido.getData()).
                valorFrete(pedido.getValorFrete()).
                itens(pedido.getItens()).
                destinatario(pedido.getDestinatario()).
                valorTotalItens(valorItensQtd + pedido.getValorFrete()
                        + itemNotaFiscalList.stream().mapToDouble(nt -> {
                    return nt.getValorTributoItem();
                }).sum()).build();

    }
}