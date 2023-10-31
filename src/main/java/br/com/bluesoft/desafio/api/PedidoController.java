package br.com.bluesoft.desafio.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.api.service.PedidoResponseService;
import br.com.bluesoft.desafio.api.service.PedidoService;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoNovoRequest;
import br.com.bluesoft.desafio.model.response.PedidoResponse;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	@Autowired
	private PedidoResponseService pedidoResponseService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@RequestMapping(path = "/novo", method = RequestMethod.POST)
	public ResponseEntity<?> adicionarPedido(@RequestBody List<PedidoNovoRequest> pedido) throws Exception {
		List<Pedido> vPed = pedidoService.gerarPedido(pedido);
		List<PedidoResponse> vRet = pedidoResponseService.tratarResposta(vPed);
		return new ResponseEntity<>(vRet, HttpStatus.OK);
	}

	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public List<PedidoResponse> listar() throws Exception {
		List<Pedido> vPed = pedidoService.todosPedidos();
		List<PedidoResponse> vRet = pedidoResponseService.tratarResposta(vPed);
		return vRet;
	}

}
