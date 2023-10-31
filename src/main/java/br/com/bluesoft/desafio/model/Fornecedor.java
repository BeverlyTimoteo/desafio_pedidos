package br.com.bluesoft.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

	@Id
	@GeneratedValue(generator = "fornecedor_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "fornecedor_id_seq", allocationSize = 1, initialValue = 1, sequenceName = "fornecedor_id_seq")
	private Long id;

	@Column(length = 20)
	private String cnpj;

	@NotNull
	@NotEmpty
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
