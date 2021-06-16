package com.antonio.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.antonio.algafood.domain.exception.EntidadeEmUsoException;
import com.antonio.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.antonio.algafood.domain.model.Cozinha;
import com.antonio.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	private static final String MSG_COZINHA_EM_USO //AULA 8.5
		= "Cozinha de código %d não pode ser removida, pois está em uso!";

	private static final String MSG_COZINHA_NAO_ENCONTRADA
		= "Não existe um cadastro de cozinha com código %d";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	//MÉTODO SALVAR
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
	}
}
