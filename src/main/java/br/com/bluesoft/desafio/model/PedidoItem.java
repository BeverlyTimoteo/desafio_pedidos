package br.com.bluesoft.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido_item")
public class PedidoItem {

	@Id
	@GeneratedValue(generator = "pedidoitem_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "pedidoitem_id_seq", allocationSize = 1, initialValue = 1, sequenceName = "pedidoitem_id_seq")
	private Long id;

	@NotNull
	@ManyToOne
	private Produto oProduto;

	@NotNull
	@Column(name = "preco_unitario")
	private double precoUnitario;

	@NotNull
	private double quantidade;

	@NotNull	
	@Column(name = "preco_total")
	private double precoTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@Valid
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", columnDefinition = "integer")
	private Pedido oPedido;

	public Pedido getoPedido() {
		return oPedido;
	}

	public void setoPedido(Pedido oPedido) {
		this.oPedido = oPedido;
	}

	public PedidoItem() {
		this.oProduto = null;
		this.precoTotal = 0;
		this.precoUnitario = 0;
		this.quantidade = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getoProduto() {
		return oProduto;
	}

	public void setoProduto(Produto oProduto) {
		this.oProduto = oProduto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

}
