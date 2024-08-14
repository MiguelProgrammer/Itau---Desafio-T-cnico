package br.com.itau.geradornotafiscal.core.usecase.notafiscal;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;

public interface GeradorNotaFiscalService{

	NotaFiscal gerarNotaFiscal(Pedido pedido);
	
}
