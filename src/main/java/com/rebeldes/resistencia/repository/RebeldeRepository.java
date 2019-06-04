package com.rebeldes.resistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rebeldes.resistencia.models.Rebelde;

public interface RebeldeRepository extends JpaRepository<Rebelde, Long>{

	 // persistencia no banco de dados (findById ....)
}
