package com.rebeldes.resistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Itens;

public interface InventarioRepository extends JpaRepository<Inventario, Long>{
	/*
	@Query("SELECT id FROM Inventario WHERE id = ?1")
	List<Itens> quatidadeDoItem(long id);
	*/
}
