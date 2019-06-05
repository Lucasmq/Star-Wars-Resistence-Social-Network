package com.rebeldes.resistencia.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.RebeldeRepository;
import com.rebeldes.resistencia.services.RebeldeService;

@RestController
@RequestMapping(value="/api")
public class RebeldeResource {
	
	@Autowired
	RebeldeService service;
	
	@GetMapping("/rebeldes")
	public List<Rebelde> listaRebeldes(){
		return service.findAll();
	}
	
	@GetMapping("/rebelde/{id}")
	public ResponseEntity<Rebelde> listaRebeldeUnico(@PathVariable(value = "id") long id){ // responseEntity para repostas HTTP
		Rebelde rebelde = service.findById(id);
		return ResponseEntity.ok().body(rebelde);
	}
	
	
	@PostMapping("/rebelde")
	public ResponseEntity<Rebelde> salvaRebelde(@RequestBody Rebelde rebelde) {  // TODO arrumar o modo como salva os itens e localização
		Rebelde reb = service.salvarRebelde(rebelde);
		return ResponseEntity.ok().body(reb);
	}
	
	@PostMapping("/rebelde/votartraidor/{id}")
	public ResponseEntity<Rebelde> votaTraidor(@PathVariable(value = "id") long id) {
		Rebelde reb = service.votarTraidor(id);
		return ResponseEntity.ok().body(reb);
	}
	
}
