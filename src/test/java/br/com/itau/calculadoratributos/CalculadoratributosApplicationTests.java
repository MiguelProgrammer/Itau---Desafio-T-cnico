package br.com.itau.calculadoratributos;

import br.com.itau.geradornotafiscal.core.domain.notafiscal.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Item;
import br.com.itau.geradornotafiscal.core.usecase.calculadora.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.impl.GeradorNotaFiscalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoratributosApplicationTests {

	@InjectMocks
	private CalculadoraAliquotaProduto calculadoraAliquotaProduto;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testaCalculosAliquota() {

		List<Item> items = new ArrayList<>();

		Item item1 = new Item();
		item1.setValorUnitario(1200);
		item1.setQuantidade(13);
		items.add(item1);

		Item item2 = new Item();
		item2.setValorUnitario(1300);
		item2.setQuantidade(12);
		items.add(item2);

		List<ItemNotaFiscal> itemNotaFiscals = calculadoraAliquotaProduto.calcularAliquota(items, 0.19);
		assertEquals(2, itemNotaFiscals.size());
		assertEquals(475, itemNotaFiscals.get(0).getValorTributoItem()
		 + itemNotaFiscals.get(1).getValorTributoItem());
	}
}
