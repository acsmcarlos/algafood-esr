package com.antonio.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException { //AULA 8:10

	private static final long serialVersionUID = 1L;
	
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Não existe um cadastro de Cidade com código %d", cidadeId));
	}
}
