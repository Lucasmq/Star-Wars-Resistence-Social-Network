package com.rebeldes.resistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rebeldes.resistencia.models.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long>{
	
}
