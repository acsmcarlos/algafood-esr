package com.antonio.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.antonio.algafood.domain.model.Cozinha;
import com.antonio.algafood.domain.model.Restaurante;
import com.antonio.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO 
		= "Nâo existe cadastro de cozinha com o código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	
	//MÉTODO SALVAR 
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId(); //fazer uma consulta de cozinha, se ela existe
		
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
	}
	
}
