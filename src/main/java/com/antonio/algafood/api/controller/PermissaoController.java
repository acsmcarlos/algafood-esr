package com.antonio.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.algafood.domain.exception.EntidadeEmUsoException;
import com.antonio.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.antonio.algafood.domain.model.Permissao;
import com.antonio.algafood.domain.repository.PermissaoRepository;
import com.antonio.algafood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {
	
	@Autowired
	private PermissaoRepository permissaoRepository; 
	
	@Autowired
	private CadastroPermissaoService cadastroPermissao;
	
	//MÉTODO LISTAR
	@GetMapping
	public List<Permissao> listar(){
		return permissaoRepository.findAll();
	}
	
	//MÉTODO BUSCAR
	@GetMapping("/{permissaoId}")
	public ResponseEntity<Permissao> buscar(@PathVariable Long permissaoId) {
		Optional<Permissao> permissao = permissaoRepository.findById(permissaoId);
		
		if(permissao.isPresent()) {
			return ResponseEntity.ok(permissao.get());
		}
		
		return ResponseEntity.notFound().build(); //404 notFound
	}
	
	//MÉTODO ADICIONAR
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Permissao adicionar(@RequestBody Permissao permissao) {
		return cadastroPermissao.salvar(permissao);
	}
	
	//MÉTODO ATUALIZAR
	@PutMapping("/{permissaoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long permissaoId,
			@RequestBody Permissao permissao) {
		try {
			Optional<Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);
			
			if(permissaoAtual.isPresent()) {
				BeanUtils.copyProperties(permissao, permissaoAtual.get(), "id");
				
				Permissao permissaoSalva = cadastroPermissao.salvar(permissaoAtual.get());
				return ResponseEntity.ok(permissaoSalva);
			}
			
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	//MÉTODO EXCLUIR
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Permissao> remover(@PathVariable Long permissaoId) {
		try {
			cadastroPermissao.excluir(permissaoId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
