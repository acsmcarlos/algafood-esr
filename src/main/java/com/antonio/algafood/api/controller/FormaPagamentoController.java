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
import org.springframework.web.bind.annotation.RestController;

import com.antonio.algafood.domain.exception.EntidadeEmUsoException;
import com.antonio.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.antonio.algafood.domain.model.FormaPagamento;
import com.antonio.algafood.domain.repository.FormaPagamentoRepository;
import com.antonio.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	
	//MÉTODO LISTAR
	@GetMapping
	public List<FormaPagamento> listar(){
		return formaPagamentoRepository.findAll();
	}
	
	//MÉTODO BUSCAR POR ID
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId) {
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);
		
		if(formaPagamento.isPresent()) {
			return ResponseEntity.ok(formaPagamento.get());
		}
		
		return ResponseEntity.notFound().build();
			
	}
	
	//MÉTODO ADICIONAR
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody FormaPagamento formaPagamento) {
		try {
			formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(formaPagamento);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	//MÉTODO ATUALIZAR
		@PutMapping("/{formaPagamentoId}")
		public ResponseEntity<?> atualizar(@PathVariable Long formaPagamentoId,
				@RequestBody FormaPagamento formaPagamento) {
			Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);
			try {
				if(formaPagamentoAtual.isPresent()) {
					BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(), "id");
					
					FormaPagamento formaPagamentoSalvo = cadastroFormaPagamento.salvar(formaPagamentoAtual.get());
					return ResponseEntity.ok(formaPagamentoSalvo);
				}
				
				return ResponseEntity.notFound().build();
			
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest()
						.body(e.getMessage());
			}
		}
	
		//MÉTODO EXCLUIR
		@DeleteMapping("/{formaPagamentoId}")
		public ResponseEntity<FormaPagamento> remover(@PathVariable Long formaPagamentoId) {
			try {
				formaPagamentoRepository.deleteById(formaPagamentoId);
				return ResponseEntity.noContent().build();
				
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.notFound().build();

			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		}

}
