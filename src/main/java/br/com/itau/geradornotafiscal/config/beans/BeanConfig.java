package br.com.itau.geradornotafiscal.config.beans;

import br.com.itau.geradornotafiscal.adapter.controller.GeradorController;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.TipoNotaFiscal;
import br.com.itau.geradornotafiscal.core.usecase.notafiscal.impl.GeradorNotaFiscalServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public GeradorController GeradorController(GeradorNotaFiscalServiceImpl notaFiscalService,
                                               TipoNotaFiscal tipoNotaFiscal) {
        return new GeradorController(tipoNotaFiscal, notaFiscalService);
    }
}
