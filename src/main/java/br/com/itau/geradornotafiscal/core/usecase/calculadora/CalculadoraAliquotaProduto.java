package br.com.itau.geradornotafiscal.core.usecase.calculadora;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Item;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraAliquotaProduto {

    public List<ItemNotaFiscal> calcularAliquota(List<Item> items, double aliquotaPercentual) {

        List<ItemNotaFiscal> itemNotaFiscalList = new ArrayList<>();

        for (Item item : items) {

            ItemNotaFiscal itemNotaFiscal = ItemNotaFiscal.builder()
                    .idItem(item.getIdItem()).descricao(item.getDescricao())
                    .valorUnitario(item.getValorUnitario()).quantidade(item.getQuantidade())
                    .valorTributoItem(item.getValorUnitario() * aliquotaPercentual)
                    .build();

            itemNotaFiscalList.add(itemNotaFiscal);
        }

        return itemNotaFiscalList;
    }
}



