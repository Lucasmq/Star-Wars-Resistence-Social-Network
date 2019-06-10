package com.rebeldes.resistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rebeldes.resistencia.models.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long>{
	//@Query("SELECT count(*) FROM Inventario i JOIN Itens it WHERE it.id = 2")
	//Long numItens(Long idItem);
	//Long countByItens(Long idItem)
}

