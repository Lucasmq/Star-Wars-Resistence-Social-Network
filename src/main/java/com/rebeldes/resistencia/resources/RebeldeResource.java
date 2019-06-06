package com.rebeldes.resistencia.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.InventarioDeTroca;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.RebeldeRepository;
import com.rebeldes.resistencia.services.InventarioService;
import com.rebeldes.resistencia.services.RebeldeService;

@RestController
@RequestMapping(value="/api")
public class RebeldeResource {
	
	@Autowired
	RebeldeService rebeldeService;
	
	@Autowired
	InventarioService inventarioService;
	
	@GetMapping("/rebeldes")
	public List<Rebelde> listaRebeldes(){
		return rebeldeService.findAll();
	}
	
	@GetMapping("/rebelde/{id}")
	public ResponseEntity<Rebelde> listaRebeldeUnico(@PathVariable(value = "id") long id){ // responseEntity para repostas HTTP
		Rebelde rebelde = rebeldeService.findById(id);
		System.out.println(rebelde.getNome());
		return ResponseEntity.ok().body(rebelde);
	}
	
	
	@PostMapping("/rebelde")
	public ResponseEntity<Rebelde> salvaRebelde(@RequestBody Rebelde rebelde) {  // TODO arrumar o modo como salva os itens e localização
		Rebelde reb = rebeldeService.salvarRebelde(rebelde);
		return ResponseEntity.ok().body(reb);
	}
	
	@PostMapping("/rebelde/votartraidor/{id}")
	public ResponseEntity<Rebelde> votaTraidor(@PathVariable(value = "id") long id) {
		Rebelde reb = rebeldeService.votarTraidor(id);
		return ResponseEntity.ok().body(reb);
	}
	
	@PostMapping("/rebeldes/{idRebelde_1}/troca/{idRebelde_2}")
	public ResponseEntity<String> trocaItens(@PathVariable(value = "idRebelde_1") long idRebelde_1, @PathVariable(value = "idRebelde_2") long idRebelde_2,@RequestBody InventarioDeTroca inventarioDeTroca) {

		Rebelde rb1 = rebeldeService.findById(idRebelde_1);
		Rebelde rb2 = rebeldeService.findById(idRebelde_2);
		
		Inventario inventario_1 = inventarioDeTroca.getInventario_1();
		Inventario inventario_2 = inventarioDeTroca.getInventario_2();

		if(inventarioService.aptoParaTrocar(rb1, rb2, inventario_1, inventario_2)) {
			System.out.println(inventario_1.pontuacaoTotalInventario());
			System.out.println(inventario_2.pontuacaoTotalInventario());
			inventarioService.trocarItensDoInventario(rb1, rb2, inventario_1, inventario_2);
		}else {
			return new ResponseEntity<>("", HttpStatus.NOT_ACCEPTABLE);
		}
		//return ResponseEntity.ok().body(rb1);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
}
