package br.com.bluesoft.desafio.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.desafio.model.PedidoItem;

public interface PedidoItemRepository extends CrudRepository<PedidoItem, Long> {
}
