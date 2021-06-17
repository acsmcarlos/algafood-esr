package com.antonio.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	MENSAGEM_INCOPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível!"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada!"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso!"),
	ERRO_NEGOCIO_EXCEPTION("/erro-negocio-exception", "Violação de regra de negócio!");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}

}
