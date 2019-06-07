package com.rebeldes.resistencia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebeldes.resistencia.models.DTO.PontosPerdidosTraidoresDTO;
import com.rebeldes.resistencia.models.DTO.RebeldesPorcentagemDTO;
import com.rebeldes.resistencia.models.DTO.RelatorioMediaItensDTO;
import com.rebeldes.resistencia.models.DTO.TraidoresPorcentagemDTO;
import com.rebeldes.resistencia.services.InventarioService;
import com.rebeldes.resistencia.services.RebeldeService;
import com.rebeldes.resistencia.services.TraidoresService;

@RestController
@RequestMapping(value="/api/relatorios")
public class RelatorioResource {   // destinado para resoulver os pedidos de relatorios
	
	@Autowired
	InventarioService inventarioService;
	
	@Autowired
	TraidoresService traidoresService;
	
	@Autowired
	RebeldeService rebeldeService;
	
	
	@GetMapping(value="/rebeldes/media/itens")
	public ResponseEntity<List<RelatorioMediaItensDTO>> mediaItens(){ // responseEntity para repostas HTTP
		List<RelatorioMediaItensDTO> mediaItens = inventarioService.mediaRecurso();
		return ResponseEntity.ok().body(mediaItens);
	}
	
	@GetMapping(value = "/traidores/porcentagem")
	public ResponseEntity<TraidoresPorcentagemDTO> porcentagemTraidores() {
		TraidoresPorcentagemDTO traidoresPorcentagem = new TraidoresPorcentagemDTO(traidoresService.porcentagemTraidores());  
		return ResponseEntity.ok().body(traidoresPorcentagem);
	}
	
	@GetMapping(value = "/rebeldes/porcentagem")
	public ResponseEntity<RebeldesPorcentagemDTO> porcentagemRebeldes() {
		RebeldesPorcentagemDTO rebeldesPorcentagem = new RebeldesPorcentagemDTO(rebeldeService.porcentagemRebeldes()); 
		return ResponseEntity.ok().body(rebeldesPorcentagem);
	}

	
	@GetMapping(value="/traidores/pontos/perdidos")
	public ResponseEntity<PontosPerdidosTraidoresDTO> pontosPerdidosTraidores(){
		PontosPerdidosTraidoresDTO ptsPerdidos = new PontosPerdidosTraidoresDTO(traidoresService.pontosPerdidosTraidores());
		return ResponseEntity.ok().body(ptsPerdidos);
	}
}
