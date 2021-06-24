package com.antonio.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.antonio.algafood.domain.model.Cidade;
import com.antonio.algafood.domain.model.Cozinha;
import com.antonio.algafood.domain.model.Restaurante;
import com.antonio.algafood.domain.model.mixin.CidadeMixin;
import com.antonio.algafood.domain.model.mixin.CozinhaMixin;
import com.antonio.algafood.domain.model.mixin.RestauranteMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() { //classe Restaurante tem uma classe RestauranteMixin
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		

	}
}
