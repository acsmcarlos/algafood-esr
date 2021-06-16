package com.antonio.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.antonio.algafood.domain.exception.EntidadeEmUsoException;
import com.antonio.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.antonio.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler /*aula 8.15*/ { //classe exception global
	
	//método pra tratar exceções dentro do controlador e retornar um response entity
		@ExceptionHandler(EntidadeNaoEncontradaException.class)
		public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
				EntidadeNaoEncontradaException e) {
			Problema problema = Problema.builder()
					.dataHora(LocalDateTime.now())
					.mensagem(e.getMessage()).build();
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(problema);
		}
		
		//método pra tratar exceções dentro do controlador e retornar um response entity
		@ExceptionHandler(NegocioException.class)
		public ResponseEntity<?> tratarNegocioException(NegocioException e) {
			Problema problema = Problema.builder()
					.dataHora(LocalDateTime.now())
					.mensagem(e.getMessage()).build();
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(problema);
		}
		
		@ExceptionHandler(EntidadeEmUsoException.class)
		public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
			Problema problema = Problema.builder()
					.dataHora(LocalDateTime.now())
					.mensagem(e.getMessage()).build();
			
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(problema);
		}

}
