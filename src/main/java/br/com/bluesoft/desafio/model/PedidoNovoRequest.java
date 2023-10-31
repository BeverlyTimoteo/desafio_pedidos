package br.com.bluesoft.desafio.model;

public class PedidoNovoRequest {

	private String gtin;
	private double quantidade;

	public PedidoNovoRequest() {
	}

	public PedidoNovoRequest(String gtin, double quantidade) {
		this.gtin = gtin;
		this.quantidade = quantidade;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

}
