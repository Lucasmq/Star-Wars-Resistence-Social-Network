package com.rebeldes.resistencia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.InventarioRepository;
import com.rebeldes.resistencia.repository.LocalizacaoRepository;
import com.rebeldes.resistencia.repository.RebeldeRepository;
import com.rebeldes.resistencia.services.exception.ObjectNotFoundException;

@Service
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository rebeldeRepo;
	
	@Autowired
	private LocalizacaoService localService;
	
	@Autowired 
	private InventarioService inventService;
	
	public List<Rebelde> findAll(){
		return rebeldeRepo.findAll();
	}
	
	public Rebelde findById(Long id) {
		Optional<Rebelde> objRb = rebeldeRepo.findById(id);
		return objRb.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Rebelde salvarRebelde(Rebelde rb) {
		localService.saveLocalizacao(rb.getLocalizacao()); // TODO validar os dados de localizacao
		inventService.saveInventario(rb.getInventario());
		return rebeldeRepo.save(rb);
	}
	
	public Rebelde votarTraidor(Long id) {
		Rebelde rebelde = findById(id); // caso não encontre, já manda a excepion do findbyid
		rebelde.addVotoTraidor();
		return rebeldeRepo.save(rebelde);
	}
	
}
