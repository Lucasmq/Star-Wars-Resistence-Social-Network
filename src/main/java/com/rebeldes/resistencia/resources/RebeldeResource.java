package com.rebeldes.resistencia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.RebeldeRepository;

@RestController
@RequestMapping(value="/api")
public class RebeldeResource {
	
	@Autowired
	RebeldeRepository rebeldeRepository;
	
	@GetMapping("/rebeldes")
	public List<Rebelde> listaRebeldes(){
		return rebeldeRepository.findAll();
	}
}
