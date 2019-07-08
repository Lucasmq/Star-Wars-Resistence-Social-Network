package com.rebeldes.resistencia.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.services.LocalizacaoService;

@CrossOrigin
@RestController
@RequestMapping(value="/api/localizacao")
public class LocalizacaoResource {
	
	@Autowired
	LocalizacaoService service;
	
	@GetMapping("/rebelde/{id}")
	public ResponseEntity<Localizacao> listaLocalizacaoRebelde(@PathVariable(value = "id") Long id){
		Localizacao local = service.findLocalizacaoByRebeldeId(id);
		return ResponseEntity.ok().body(local);
	}
	
	@PutMapping("/rebelde/{id}")
	public ResponseEntity<Localizacao> atualizaLocalizacaoRebelde(@PathVariable(value = "id") Long id, @RequestBody @Valid Localizacao local){
		service.updateLocalizacaoByRebeldeId(id, local);
		return ResponseEntity.noContent().build();
	}

}
