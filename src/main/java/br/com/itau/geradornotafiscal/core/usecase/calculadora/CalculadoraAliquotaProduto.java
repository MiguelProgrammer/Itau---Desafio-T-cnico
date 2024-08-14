package br.com.itau.geradornotafiscal.core.usecase.calculadora;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Item;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraAliquotaProduto {

    private List<ItemNotaFiscal> itemNotaFiscalList = new ArrayList<>();

    public List<ItemNotaFiscal> calcularAliquota(List<Item> items, double aliquotaPercentual) {

        for (Item item : items) {

            double valorTributo = item.getValorUnitario() * aliquotaPercentual;

            ItemNotaFiscal itemNotaFiscal = ItemNotaFiscal.builder()
                    .idItem(item.getIdItem())
                    .descricao(item.getDescricao())
                    .valorUnitario(item.getValorUnitario())
                    .quantidade(item.getQuantidade())
                    .valorTributoItem(valorTributo)
                    .build();

            itemNotaFiscalList.add(itemNotaFiscal);
        }

        return itemNotaFiscalList;
    }
}



