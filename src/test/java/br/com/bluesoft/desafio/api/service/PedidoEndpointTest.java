package br.com.bluesoft.desafio.api.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoNovoRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PedidoEndpointTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PedidoService pedidoService;

	@Test
	public void inserirPedidoTeste() throws Exception {
		List<PedidoNovoRequest> vPedidos = new ArrayList<>();
		vPedidos.add(new PedidoNovoRequest("7894900011517", 10));
		vPedidos.add(new PedidoNovoRequest("7891910000197", 20));
		vPedidos.add(new PedidoNovoRequest("7892840222949", 30));
		vPedidos.add(new PedidoNovoRequest("7891910007110", 40));
		vPedidos.add(new PedidoNovoRequest("7891000053508", 50));
		vPedidos.add(new PedidoNovoRequest("7891000100103", 60));
		vPedidos.add(new PedidoNovoRequest("7891991010856", 70));

		mvc.perform(MockMvcRequestBuilders.post("/api/pedidos/novo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vPedidos)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		List<Pedido> vPedido = pedidoService.todosPedidos();

		vPedido.forEach((p) -> {
			System.out.println(p.getoFornecedor().getNome());
			System.out.println((p.getvProdutos().isEmpty() ? "Nenhum produto" : p.getvProdutos().size()));
		});

	}

	@Test
	public void naoInserirPedidoTeste() throws Exception {
		List<PedidoNovoRequest> vPedidos = new ArrayList<>();
		vPedidos.add(new PedidoNovoRequest("7891000053508", 0));

		mvc.perform(MockMvcRequestBuilders.post("/api/pedidos/novo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vPedidos)))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}

}
