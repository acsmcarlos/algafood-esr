package com.antonio.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.antonio.algafood.domain.model.Restaurante;

public interface RestauranteRepository 
	extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, 
	JpaSpecificationExecutor<Restaurante>{
	
	@Query("from Restaurante r left join fetch r.cozinha") //aula 6.14
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

}