package com.rebeldes.resistencia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.RebeldeRepository;
import com.rebeldes.resistencia.services.exception.ObjectNotFoundException;

@Service
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository repo;
	
	public List<Rebelde> findAll(){
		return repo.findAll();
	}
	
	public Rebelde findById(Long id) {
		Optional<Rebelde> objRb = repo.findById(id);
		return objRb.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Rebelde salvarRebelde(Rebelde rb) {
		return repo.save(rb);
	}
	
	public Rebelde votarTraidor(Long id) {
		Rebelde rebelde = findById(id); // caso não encontre, já manda a excepion do findbyid
		rebelde.addVotoTraidor();
		return repo.save(rebelde);
	}
	
}
