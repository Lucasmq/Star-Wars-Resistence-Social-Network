package com.rebeldes.resistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rebeldes.resistencia.models.Rebelde;

public interface RebeldeRepository extends JpaRepository<Rebelde, Long>{

	 // persistencia no banco de dados (findById ....)
	@Query("SELECT reb FROM Rebelde reb WHERE reb.traidor = 'true'")
	List<Rebelde> traidores();
	
	@Query("SELECT reb FROM Rebelde reb WHERE reb.traidor = 'false'")
	List<Rebelde> rebeldesNaoTraidores();
}
