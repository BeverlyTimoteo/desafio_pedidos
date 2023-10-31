package br.com.bluesoft.desafio.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.response.PedidoItensResponse;
import br.com.bluesoft.desafio.model.response.PedidoResponse;

@Service
public class PedidoResponseService {

	public List<PedidoResponse> tratarResposta(List<Pedido> vPed) throws Exception {

		List<PedidoResponse> vRet = new ArrayList<>();

		for (Pedido ped : vPed) {
			PedidoResponse retorno = new PedidoResponse();
			retorno.setId(ped.getId());
			retorno.getFornecedor().setNome(ped.getoFornecedor().getNome());

			adicionarItens(ped, retorno);

			vRet.add(retorno);
		}

		return vRet;
	}

	private void adicionarItens(Pedido ped, PedidoResponse retorno) {
		for (PedidoItem item : ped.getvProdutos()) {
			PedidoItensResponse itemRet = new PedidoItensResponse();
			itemRet.getProduto().setNome(item.getoProduto().getNome());
			itemRet.setPreco(item.getPrecoUnitario());
			itemRet.setQuantidade(item.getQuantidade());
			itemRet.setTotal(item.getPrecoTotal());

			retorno.getvItens().add(itemRet);
		}
	}

}
