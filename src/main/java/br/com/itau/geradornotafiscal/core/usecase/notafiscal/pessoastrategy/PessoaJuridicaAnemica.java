package br.com.itau.geradornotafiscal.core.usecase.notafiscal.pessoastrategy;

import br.com.itau.geradornotafiscal.core.domain.enums.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.core.domain.enums.TipoPessoa;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;
import br.com.itau.geradornotafiscal.core.usecase.calculadora.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.TipoNotaFiscal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PessoaJuridicaAnemica implements TipoNotaFiscal {

    private CalculadoraAliquotaProduto calcularAliquota = new CalculadoraAliquotaProduto();

    @Override
    public List<ItemNotaFiscal> calcula(Pedido pedido, TipoPessoa tipoPessoa) {

        List<ItemNotaFiscal> itemNotaFiscals = new ArrayList<>();
        RegimeTributacaoPJ regimeTributacao = pedido.getDestinatario().getRegimeTributacao();

        if (tipoPessoa.equals(TipoPessoa.JURIDICA)) {

            if (regimeTributacao == RegimeTributacaoPJ.SIMPLES_NACIONAL) {
                itemNotaFiscals = this.calcularAliquota.calcularAliquota(pedido.getItens(), this.calculaAliquoteSN(pedido));
            }

            if (regimeTributacao == RegimeTributacaoPJ.LUCRO_REAL) {
                itemNotaFiscals =  this.calcularAliquota.calcularAliquota(pedido.getItens(), this.calculaAliquotaLR(pedido));
            }

            if (regimeTributacao == RegimeTributacaoPJ.LUCRO_PRESUMIDO) {
                itemNotaFiscals = calcularAliquota.calcularAliquota(pedido.getItens(),
                        this.calculaAliquotaLP(pedido));
            }

        }

        return itemNotaFiscals;
    }

    private double calculaAliquotaLP(Pedido pedido) {

        double aliquota = 0.20;

        if (pedido.getValorTotalItens() < 1000) {
            aliquota = 0.03;
        }

        if (pedido.getValorTotalItens() <= 2000) {
            aliquota = 0.09;
        }

        if (pedido.getValorTotalItens() <= 5000) {
            aliquota = 0.16;
        }

        return aliquota;
    }

    private double calculaAliquotaLR(Pedido pedido) {

        double aliquota = 0.20;

        if (pedido.getValorTotalItens() < 1000) {
            aliquota = 0.03;
        }

        if (pedido.getValorTotalItens() <= 2000) {
            aliquota = 0.09;
        }

        if (pedido.getValorTotalItens() <= 5000) {
            aliquota = 0.15;
        }

        return aliquota;
    }

    private double calculaAliquoteSN(Pedido pedido) {

        double aliquota = 0.19;

        if (pedido.getValorTotalItens() <= 5000) {
            aliquota = 0.13;
        }

        if (pedido.getValorTotalItens() <= 2000) {
            aliquota = 0.07;
        }

        if (pedido.getValorTotalItens() < 1000) {
            aliquota = 0.03;
        }

        return aliquota;
    }

}

