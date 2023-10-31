package br.com.bluesoft.desafio.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.desafio.api.exception.PedidoNaoGeradoException;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.PedidoNovoRequest;
import br.com.bluesoft.desafio.model.service.Precos;
import br.com.bluesoft.desafio.model.service.PrecosFornecedores;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	ProdutoFornecedorService produtoFornecedorService;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Pedido> gerarPedido(List<PedidoNovoRequest> pedido) throws Exception {
		if (pedido.isEmpty())
			throw new PedidoNaoGeradoException("Nenhum produto informado!");

		List<Pedido> vPed = new ArrayList<Pedido>();

		pedido.forEach((p) -> {
			try {
				if (p.getQuantidade() <= 0)
					return;					
				
				List<PrecosFornecedores> consulta = produtoFornecedorService.getProdutoFornecedor(p.getGtin());

				if (consulta.isEmpty())
					return;					

				PedidoItem oItem = new PedidoItem();
				Fornecedor oFornecedor = new Fornecedor();

				aplicarCondicaoPedido(consulta, p.getQuantidade(), oItem, oFornecedor);

				oItem.setoProduto(produtoRepository.findOne(p.getGtin()));
				oItem.setQuantidade(p.getQuantidade());
				
				if (oItem.getPrecoUnitario() <= 0)
					throw new PedidoNaoGeradoException(String.format("Nenhum fornecedor encontrado para a quantidade solicitada do produto %s", oItem.getoProduto().getNome()));				
				
				adicionarPedido(vPed, oItem, oFornecedor);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		salvarPedido(vPed);

		return vPed;
	}

	private void adicionarPedido(List<Pedido> vPed, PedidoItem oItem, Fornecedor oFornecedor) throws Exception {
		Pedido ped = getPedido(vPed, oFornecedor);

		if (Objects.isNull(ped)) {
			ped = criarPedidoFornecedor(oFornecedor);
			vPed.add(ped);
		}

		oItem.setPrecoTotal(oItem.getPrecoUnitario() * oItem.getQuantidade());

		ped.AddItem(oItem);
	}

	private Pedido getPedido(List<Pedido> vPed, Fornecedor oFornecedor) throws Exception {
		Pedido pedF = null;

		for (Pedido ped : vPed) {
			if (ped.getoFornecedor().getCnpj().equals(oFornecedor.getCnpj())) {
				pedF = ped;
				break;
			}
		}

		return pedF;
	}

	private Pedido criarPedidoFornecedor(Fornecedor oFornecedor) throws Exception {
		Pedido ped = new Pedido();

		Fornecedor oF = fornecedorRepository.findByCnpj(oFornecedor.getCnpj());

		if (Objects.isNull(oF))
			oF = oFornecedor;

		ped.setoFornecedor(oF);
		return ped;
	}

	@Transactional
	private void salvarPedido(List<Pedido> vPed) throws Exception {
		if (vPed.isEmpty())
			throw new PedidoNaoGeradoException("Nenhum produto adicionado para gerar o pedido!");
		
		pedidoRepository.save(vPed);
	}

	private void aplicarCondicaoPedido(List<PrecosFornecedores> oConsulta, double qtd, PedidoItem oItem, Fornecedor oFornecedor) throws Exception {
		for (PrecosFornecedores precoFor : oConsulta) {
			for (Precos itemFor : precoFor.getvPrecos()) {
				if (qtd >= itemFor.getQuantidadeMinima() && (oItem.getPrecoUnitario() == 0 || oItem.getPrecoUnitario() > itemFor.getPreco())) {
					oItem.setPrecoUnitario(itemFor.getPreco());
					oFornecedor.setCnpj(precoFor.getCnpj());
					oFornecedor.setNome(precoFor.getNome());
				}
			}
		}
	}

	public List<Pedido> todosPedidos() throws Exception {
		return (List<Pedido>) pedidoRepository.findAll();
	}

}
