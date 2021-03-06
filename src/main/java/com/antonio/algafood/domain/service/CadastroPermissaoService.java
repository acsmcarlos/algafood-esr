package com.antonio.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonio.algafood.domain.exception.EntidadeEmUsoException;
import com.antonio.algafood.domain.exception.NegocioException;
import com.antonio.algafood.domain.model.Permissao;
import com.antonio.algafood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	@Transactional
	public void excluir(Long permissaoId) {
		try {
			permissaoRepository.deleteById(permissaoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(
					String.format("Não existe um cadastro de permissao com código %d", permissaoId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Permissao de código %d não pode ser removida, pois está em uso!", permissaoId));
		}
	}
}
