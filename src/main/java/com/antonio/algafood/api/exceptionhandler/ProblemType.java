package com.antonio.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível!"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema!"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido!"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado!"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso!"),
	ERRO_NEGOCIO_EXCEPTION("/erro-negocio-exception", "Violação de regra de negócio!");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}

}
