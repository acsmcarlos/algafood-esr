package com.antonio.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException { //AULA 8:10

	private static final long serialVersionUID = 1L;
	
	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de Cozinha com código %d", cozinhaId));
	}
}
