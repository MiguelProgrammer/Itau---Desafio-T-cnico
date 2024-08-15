package br.com.itau.geradornotafiscal.core.usecase.notafiscal.pessoastrategy;

import br.com.itau.geradornotafiscal.core.domain.enums.TipoPessoa;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;
import br.com.itau.geradornotafiscal.core.usecase.calculadora.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.TipoNotaFiscal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaFisicaAnemica implements TipoNotaFiscal {

    private CalculadoraAliquotaProduto calcularAliquota = new CalculadoraAliquotaProduto();

    @Override
    public List<ItemNotaFiscal> calcula(Pedido pedido, TipoPessoa tipoPessoa) {

        if (tipoPessoa.equals(TipoPessoa.FISICA)) {
            return calcularAliquota.calcularAliquota(pedido.getItens(),
                    this.calculaAliquota(pedido));
        }

        return new PessoaJuridicaAnemica().calcula(pedido, tipoPessoa);
    }

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
