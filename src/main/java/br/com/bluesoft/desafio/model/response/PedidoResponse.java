package br.com.bluesoft.desafio.model.response;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PedidoResponse {

	private Long id;
	private PedidoFornecedorResponse fornecedor;
	
	@JsonProperty("itens")
	private List<PedidoItensResponse> vItens;

	public PedidoResponse() {
		this.fornecedor = new PedidoFornecedorResponse();
		this.vItens = new LinkedList<PedidoItensResponse>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PedidoFornecedorResponse getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(PedidoFornecedorResponse fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<PedidoItensResponse> getvItens() {
		return vItens;
	}

	public void setvItens(List<PedidoItensResponse> vItens) {
		this.vItens = vItens;
	}

}
