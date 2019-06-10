package com.rebeldes.resistencia.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.InventarioRepository;
import com.rebeldes.resistencia.repository.RebeldeRepository;
import com.rebeldes.resistencia.services.exception.ObjectNotFoundException;

@Service
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository rebeldeRepo;
	
	@Autowired
	private InventarioRepository inventarioRepo;
	
	@Autowired
	private LocalizacaoService localService;
	
	@Autowired 
	private InventarioService inventService;
	
	@Autowired 
	private TraidoresService traidoresService;
	
	public List<Rebelde> findAll(){
		return rebeldeRepo.findAll();
	}
	
	public List<Rebelde> findAllRebeldes(){         // não está incluido os traidores
		return rebeldeRepo.rebeldesNaoTraidores();
	}
	
	public Rebelde findById(Long id) {
		Optional<Rebelde> objRb = rebeldeRepo.findById(id);
		return objRb.orElseThrow(() -> new ObjectNotFoundException("Rebelde não encontrado"));
	}
	
	public Rebelde salvarRebelde(Rebelde rb) {
		//System.out.println();//inventarioRepo.numItens(2L));
		localService.saveLocalizacao(rb.getLocalizacao()); // TODO validar os dados de localizacao
		inventService.saveInventario(rb.getInventario());
		return rebeldeRepo.save(rb);
	}
	
	public double porcentagemRebeldes() {
		return (100-traidoresService.porcentagemTraidores());
	}

}
