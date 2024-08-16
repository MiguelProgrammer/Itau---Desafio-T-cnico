package br.com.itau.geradornotafiscal.web.resource;

import br.com.itau.geradornotafiscal.adapter.controller.GeradorController;
import br.com.itau.geradornotafiscal.core.domain.notafiscal.NotaFiscal;

import br.com.itau.geradornotafiscal.core.domain.pedido.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class GeradorNFController {

	private GeradorController geradorController;

	public GeradorNFController(GeradorController geradorController) {
		this.geradorController = geradorController;
	}

	@PostMapping("/gerar-nota-fiscal")
	public ResponseEntity<NotaFiscal> gerarNotaFiscal(@RequestBody Pedido pedido) {
		NotaFiscal notaFiscal = geradorController.gerarNota(pedido);
		return new ResponseEntity<>(notaFiscal, HttpStatus.OK);
	}
	
}
