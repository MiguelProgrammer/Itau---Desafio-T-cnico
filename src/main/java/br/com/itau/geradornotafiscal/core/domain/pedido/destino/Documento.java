package br.com.itau.geradornotafiscal.core.domain.pedido.destino;

import br.com.itau.geradornotafiscal.core.domain.enums.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Documento {

    @JsonProperty("numero")
    private String numero;

    @JsonProperty("tipo")
    private TipoDocumento tipo;

}
