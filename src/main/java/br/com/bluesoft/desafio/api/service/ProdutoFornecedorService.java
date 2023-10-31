package br.com.bluesoft.desafio.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bluesoft.desafio.model.service.PrecosFornecedores;

@Service
public class ProdutoFornecedorService {

	final String uri = "https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev/produto/";

	@Autowired
	private RestTemplate restTemplate;

	public List<PrecosFornecedores> getProdutoFornecedor(String gtin) throws Exception {
		String resultado = restTemplate.getForObject(uri.concat(gtin), String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(resultado, new TypeReference<List<PrecosFornecedores>>() {});
	}

}
