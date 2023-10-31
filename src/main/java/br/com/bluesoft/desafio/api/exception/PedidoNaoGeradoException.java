package br.com.bluesoft.desafio.api.exception;

public class PedidoNaoGeradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PedidoNaoGeradoException(String mensagem) {
		super(mensagem);
	}
}
