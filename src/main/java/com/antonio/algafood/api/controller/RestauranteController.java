package com.antonio.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.algafood.domain.model.Estado;
import com.antonio.algafood.domain.model.Restaurante;
import com.antonio.algafood.domain.repository.RestauranteRepository;
import com.antonio.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	
	// MÉTODO LISTAR
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}

	//MÉTODO BUSCAR POR ID
		@GetMapping("/{restauranteId}")
		public Restaurante buscar(@PathVariable Long restauranteId) {
			return cadastroRestaurante.buscarOuFalhar(restauranteId);
		}
	
	//MÉTODO ADICIONAR
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		return cadastroRestaurante.salvar(restaurante);
	}
	
	//MÉTODO ATUALIZAR
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId,
			@RequestBody Estado estado) {
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
		BeanUtils.copyProperties(estado, restauranteAtual, "id");
		return cadastroRestaurante.salvar(restauranteAtual);
	}
	
//	@PatchMapping("/{restauranteId}")
//	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
//			@RequestBody Map<String, Object> campos) {
//		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
//		
//		if(restauranteAtual == null) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		merge(campos, restauranteAtual.get());
//		
//		return atualizar(restauranteId, restauranteAtual.get());
//	}

//	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
//		
//		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//			field.setAccessible(true);
//			
//			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//			
////			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
//			
//			ReflectionUtils.setField(field, restauranteDestino, novoValor);
//		});
//	}
	
//	//MÉTODO EXCLUIR ATUAL //aula 8.2
//		@DeleteMapping("/{restauranteId}")
//		@ResponseStatus(HttpStatus.NO_CONTENT)
//		public void remover(@PathVariable Long restauranteId) {
//			cadastroRestaurante.buscarOuFalhar(restauranteId);
//		}
}
