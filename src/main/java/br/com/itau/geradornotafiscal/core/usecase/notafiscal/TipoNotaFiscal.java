package br.com.itau.geradornotafiscal.core.usecase.notafiscal;

import br.com.itau.geradornotafiscal.core.domain.enums.TipoPessoa;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;

import java.util.List;

public interface TipoNotaFiscal {

    List<ItemNotaFiscal> calcula(Pedido pedido, TipoPessoa tipoPessoa);
}
