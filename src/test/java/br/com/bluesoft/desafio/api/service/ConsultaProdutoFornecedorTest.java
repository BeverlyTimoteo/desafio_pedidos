package br.com.bluesoft.desafio.api.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.api.service.ProdutoFornecedorService;
import br.com.bluesoft.desafio.model.service.PrecosFornecedores;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsultaProdutoFornecedorTest {
	
	@Autowired
	private ProdutoFornecedorService produtoFornecedorService;

	@Test
	public void testRequestApi() throws Exception {
		List<PrecosFornecedores> o = produtoFornecedorService.getProdutoFornecedor("7894900011517");

		for (PrecosFornecedores p : o) {
			assertTrue(p.getNome().contains("Fornec"));
		}
	}

}
