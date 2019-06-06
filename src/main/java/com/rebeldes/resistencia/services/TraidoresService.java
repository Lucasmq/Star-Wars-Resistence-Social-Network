package com.rebeldes.resistencia.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Itens;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.models.DTO.RelatorioMediaItensDTO;
import com.rebeldes.resistencia.repository.InventarioRepository;
import com.rebeldes.resistencia.repository.ItensRepository;
import com.rebeldes.resistencia.repository.LocalizacaoRepository;
import com.rebeldes.resistencia.repository.RebeldeRepository;
import com.rebeldes.resistencia.services.exception.ObjectNotFoundException;

@Service
public class TraidoresService {
	
	@Autowired
	private RebeldeRepository rebeldeRepo;
	
	@Autowired
	private LocalizacaoService localService;
	
	@Autowired 
	private InventarioService inventService;
	
	@Autowired 
	private ItensService itensService;
	
	@Autowired 
	private RebeldeService rebelService;
	
	public List<Rebelde> findAll(){
		return rebeldeRepo.findAll();
	}
	
	public Rebelde votarTraidor(Long id) {
		Rebelde rebelde = rebelService.findById(id); // caso não encontre, já manda a excepion do findbyid
		rebelde.addVotoTraidor();
		return rebeldeRepo.save(rebelde);
	}
	
	public double porcentagemTraidores() {
		double traidores = rebeldeRepo.traidores().size();
		double rebeldes  = rebeldeRepo.findAll().size();
		
		return (traidores/rebeldes)*100;
	}
	
	public List<Rebelde> listaTraidores(){
		return rebeldeRepo.traidores();
	}
	
	public int pontosPerdidosTraidores() {
		int pontosPerdidos = 0;
		List<Rebelde> traidores = listaTraidores();
		for (Rebelde traidor : traidores) {
			pontosPerdidos += traidor.getInventario().pontuacaoTotalInventario();
		}
		return pontosPerdidos;
	}

}
