package br.com.bluesoft.desafio.model.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrecosFornecedores {

	private String nome;
	private String cnpj;

	@JsonProperty("precos")
	List<Precos> vPrecos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Precos> getvPrecos() {
		return vPrecos;
	}

	public void setvPrecos(List<Precos> vPrecos) {
		this.vPrecos = vPrecos;
	}
	
}
