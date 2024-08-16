package br.com.itau.calculadoratributos;

import br.com.itau.geradornotafiscal.core.domain.enums.Finalidade;
import br.com.itau.geradornotafiscal.core.domain.enums.Regiao;
import br.com.itau.geradornotafiscal.core.domain.enums.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.core.domain.enums.TipoPessoa;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;
import br.com.itau.geradornotafiscal.core.domain.pedido.Item;
import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;
import br.com.itau.geradornotafiscal.core.domain.pedido.destino.Destinatario;
import br.com.itau.geradornotafiscal.core.domain.pedido.destino.Endereco;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.TipoNotaFiscal;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.impl.GeradorNotaFiscalServiceImpl;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.pessoastrategy.PessoaFisicaAnemica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeradorControllerNotaFiscalServiceImplTest {

    @Mock
    TipoNotaFiscal tipoNotaFiscal = new PessoaFisicaAnemica();

    @InjectMocks
    private GeradorNotaFiscalServiceImpl geradorNotaFiscalService
            = new GeradorNotaFiscalServiceImpl(tipoNotaFiscal);

    @BeforeEach
    public void setup() {
        TipoNotaFiscal tipoNotaFiscal;
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGenerateNotaFiscalForTipoPessoaFisicaWithValorTotalItensLessThan500() {

        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(400);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.FISICA);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.SUDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(100);
        item.setQuantidade(4);
        List<Item> itens = new ArrayList<>();
        itens.add(item);
        pedido.setItens(itens);

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorFrete() + pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, pedido.getItens().size());
        assertEquals(Boolean.TRUE, notaFiscal.getItens().isEmpty());
    }

    @Test
    public void shouldGenerateNotaFiscalForTipoPessoaJuridicaWithRegimeTributacaoLucroPresumidoAndValorTotalItensGreaterThan5000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(6000);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.SUDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(6);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens() + pedido.getValorFrete(), notaFiscal.getValorTotalItens());
        assertEquals(1, pedido.getItens().size());
        assertEquals(item.getValorUnitario(), pedido.getItens().get(0).getValorUnitario());
    }

}