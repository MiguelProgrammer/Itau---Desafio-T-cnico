package br.com.itau.geradornotafiscal.core.domain.pedido;

import br.com.itau.geradornotafiscal.core.domain.pedido.destino.Destinatario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @JsonProperty("id_pedido")
    private int idPedido;

    @JsonProperty("data")
    private LocalDate data;

    @JsonProperty("valor_total_itens")
    private double valorTotalItens;

    @JsonProperty("valor_frete")
    private double valorFrete;

    @JsonProperty("itens")
    private List<Item> itens;

    @JsonProperty("destinatario")
    private Destinatario destinatario;

}
