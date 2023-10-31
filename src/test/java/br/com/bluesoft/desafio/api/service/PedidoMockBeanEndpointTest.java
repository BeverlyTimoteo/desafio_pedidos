package br.com.bluesoft.desafio.api.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoNovoRequest;
import br.com.bluesoft.desafio.model.response.PedidoItensResponse;
import br.com.bluesoft.desafio.model.response.PedidoResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PedidoMockBeanEndpointTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PedidoService pedidoService;

	@MockBean
	private PedidoResponseService pedidoResponseService;

	@Test
	public void retornoPedidoTeste() throws Exception {
		List<PedidoResponse> vRet = new ArrayList<>();

		PedidoResponse retorno = new PedidoResponse();
		retorno.setId(1l);
		retorno.getFornecedor().setNome("Fornecedor 3");

		PedidoItensResponse itemRet = new PedidoItensResponse();
		itemRet.getProduto().setNome("TESTE");
		itemRet.setPreco(1.99);
		itemRet.setQuantidade(5);
		itemRet.setTotal(9.95);

		retorno.getvItens().add(itemRet);

		vRet.add(retorno);

		BDDMockito.when(pedidoResponseService.tratarResposta(BDDMockito.anyListOf(Pedido.class))).thenReturn(vRet);

		String jsonReturn = objectMapper.writeValueAsString(vRet);

		List<PedidoNovoRequest> vPedidos = new ArrayList<>();
		vPedidos.add(new PedidoNovoRequest("7891000053508", 10));

		mvc.perform(MockMvcRequestBuilders.post("/api/pedidos/novo")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(vPedidos)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonReturn));
	}

}
