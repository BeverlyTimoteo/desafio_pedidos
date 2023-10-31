package br.com.bluesoft.desafio.model.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Precos {

	private double preco;

	@JsonProperty("quantidade_minima")
	private double quantidadeMinima;

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(double quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

}
