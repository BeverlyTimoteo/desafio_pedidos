package br.com.bluesoft.desafio.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@NamedEntityGraph(name = "Pedido.itens", attributeNodes = @NamedAttributeNode("vProdutos"))
public class Pedido {

	@Id
	@GeneratedValue(generator = "pedido_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "pedido_id_seq", allocationSize = 1, initialValue = 1, sequenceName = "pedido_id_seq")
	@Column(name = "id")
	private Long id;

	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Fornecedor oFornecedor;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "oPedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoItem> vProdutos;

	public Pedido() {
		oFornecedor = new Fornecedor();
		vProdutos = new LinkedList<>();
	}

	public void AddItem(PedidoItem item) {
		item.setoPedido(this);
		this.vProdutos.add(item);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fornecedor getoFornecedor() {
		return oFornecedor;
	}

	public void setoFornecedor(Fornecedor oFornecedor) {
		this.oFornecedor = oFornecedor;
	}

	public List<PedidoItem> getvProdutos() {
		return vProdutos;
	}

	public void setvProdutos(List<PedidoItem> vProdutos) {
		this.vProdutos = vProdutos;
	}

}
