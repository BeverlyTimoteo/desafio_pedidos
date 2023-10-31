package br.com.bluesoft.desafio.model.response;

public class PedidoItensResponse {

	private PedidoProdutoResponse produto;
	private double quantidade;
	private double total;
	private double preco;

	public PedidoItensResponse() {
		this.produto = new PedidoProdutoResponse();
	}

	public PedidoProdutoResponse getProduto() {
		return produto;
	}

	public void setProduto(PedidoProdutoResponse produto) {
		this.produto = produto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
