package com.antonio.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antonio.algafood.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
}
