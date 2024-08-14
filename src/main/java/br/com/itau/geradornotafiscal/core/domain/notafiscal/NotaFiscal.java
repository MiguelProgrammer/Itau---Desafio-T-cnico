package br.com.itau.geradornotafiscal.core.domain.notafiscal;

import br.com.itau.geradornotafiscal.core.domain.pedido.destino.Destinatario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscal {

    @JsonProperty("id_nota_fiscal")
    private String idNotaFiscal;

    @JsonProperty("data")
    private LocalDateTime data;

    @JsonProperty("valor_total_itens")
    private double valorTotalItens;

    @JsonProperty("valor_frete")
    private double valorFrete;

    @JsonProperty("itens")
    private List<ItemNotaFiscal> itens;

    @JsonProperty("destinatario")
    private Destinatario destinatario;

}