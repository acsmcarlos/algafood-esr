package com.antonio.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException { //AULA 8:10

	private static final long serialVersionUID = 1L;
	
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe um cadastro de Restaurante com código %d", restauranteId));
	}
}
