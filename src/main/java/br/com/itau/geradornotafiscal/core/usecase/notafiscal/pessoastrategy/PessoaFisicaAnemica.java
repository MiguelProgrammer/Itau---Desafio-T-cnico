package br.com.itau.geradornotafiscal.core.usecase.notafiscal.pessoastrategy;

import br.com.itau.geradornotafiscal.core.domain.enums.TipoPessoa;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;
import br.com.itau.geradornotafiscal.core.usecase.calculadora.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.TipoNotaFiscal;

import java.util.List;

public class PessoaFisicaAnemica implements TipoNotaFiscal {

    private CalculadoraAliquotaProduto calcularAliquota = new CalculadoraAliquotaProduto();

    /**
     * RESOURCES
     *
     * @param pedido
     * @param tipoPessoa
     * @return
     */

    @Override
    public List<ItemNotaFiscal> calcula(Pedido pedido, TipoPessoa tipoPessoa) {
        return calcularAliquota.calcularAliquota(pedido.getItens(), this.calculaAliquota(pedido));
    }

    /**
     * INTERNAL CLASS SERVICES
     *
     * @param pedido
     * @return
     */
    private double calculaAliquota(Pedido pedido) {

        double aliquota = 0.17;

        if (pedido.getValorTotalItens() <= 3500) {
            aliquota = 0.15;
        }

        if (pedido.getValorTotalItens() <= 2000) {
            aliquota = 0.12;
        }

        if (pedido.getValorTotalItens() < 500) {
            aliquota = 0;
        }

        return aliquota;
    }

}
