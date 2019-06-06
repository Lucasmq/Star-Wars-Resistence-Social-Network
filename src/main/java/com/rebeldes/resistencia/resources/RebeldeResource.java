package com.rebeldes.resistencia.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.models.DTO.InventarioDeTrocaDTO;
import com.rebeldes.resistencia.models.DTO.RebeldeDTO;
import com.rebeldes.resistencia.models.DTO.RelatorioMediaItensDTO;
import com.rebeldes.resistencia.services.InventarioService;
import com.rebeldes.resistencia.services.RebeldeService;
import com.rebeldes.resistencia.services.TraidoresService;

@RestController
@RequestMapping(value="/api")
public class RebeldeResource {
	
	@Autowired
	RebeldeService rebeldeService;
	
	@Autowired
	TraidoresService traidorService;
	
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
	public ResponseEntity<Rebelde> salvaRebelde(@RequestBody RebeldeDTO rebeldeDTO) {  // TODO arrumar o modo como salva os itens e localização
		Rebelde reb = rebeldeService.salvarRebelde(rebeldeDTO.tranformaParaRebelde());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reb.getId()).toUri(); // retorna o caminho do novo rebelde
		return ResponseEntity.created(uri).body(reb); 
	}
	
	@PostMapping("/rebelde/votartraidor/{id}")
	public ResponseEntity<Rebelde> votaTraidor(@PathVariable(value = "id") long id) {
		Rebelde reb = traidorService.votarTraidor(id);
		return ResponseEntity.ok().body(reb);
	}
	
	@PostMapping("/rebeldes/{idRebelde_1}/troca/{idRebelde_2}")
	public ResponseEntity<String> trocaItens(@PathVariable(value = "idRebelde_1") long idRebelde_1, @PathVariable(value = "idRebelde_2") long idRebelde_2,@RequestBody InventarioDeTrocaDTO inventarioDeTroca) {

		Rebelde rb1 = rebeldeService.findById(idRebelde_1);																	// a criação inventarioDeTrocaDTO foi para que possa ser possivel pegar 2 objetos do @RequestBody
		Rebelde rb2 = rebeldeService.findById(idRebelde_2);
		
		Inventario inventario_1 = inventarioDeTroca.getInventario_1();
		Inventario inventario_2 = inventarioDeTroca.getInventario_2();
		
		System.out.println(inventarioService.pontuacaoEquivalenteEntreInventarios(inventario_1, inventario_2));

		if(inventarioService.aptoParaTrocar(rb1, rb2, inventario_1, inventario_2)) {
			System.out.println(inventario_1.pontuacaoTotalInventario());
			System.out.println(inventario_2.pontuacaoTotalInventario());
			inventarioService.trocarItensDoInventario(rb1, rb2, inventario_1, inventario_2);
		}else {
			return new ResponseEntity<>("Troca não efetuada", HttpStatus.NOT_ACCEPTABLE);
		}
		//return ResponseEntity.ok().body(rb1);
		return new ResponseEntity<>("Troca efetudada com sucesso", HttpStatus.OK);
	}
	
	@GetMapping("/relatorio/rebeldes/media/itens")
	public ResponseEntity<List<RelatorioMediaItensDTO>> mediaItens(){ // responseEntity para repostas HTTP
		List<RelatorioMediaItensDTO> mediaItens = inventarioService.mediaRecurso();
		return ResponseEntity.ok().body(mediaItens);
	}
	
}
