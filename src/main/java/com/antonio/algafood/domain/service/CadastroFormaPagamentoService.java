package com.antonio.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.algafood.domain.model.FormaPagamento;
import com.antonio.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	//MÉTODO SALVAR
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

}
