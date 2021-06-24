package com.antonio.algafood.domain.model.mixin;


import com.antonio.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;

}
