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
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository rebeldeRepo;
	
	@Autowired
	private LocalizacaoService localService;
	
	@Autowired 
	private InventarioService inventService;
	
	@Autowired 
	private ItensService itensService;
	
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
	
	public double porcentagemTraidores() {
		double traidores = rebeldeRepo.traidores().size();
		double rebeldes  = rebeldeRepo.findAll().size();
		
		return (traidores/rebeldes)*100;
	}
	
	public double porcentagemRebeldes() {
		return (100-porcentagemTraidores());
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
	/*
	public void mediaRecurso() {
		
		List<RelatorioMediaItensDTO> mediasItens = new ArrayList<RelatorioMediaItensDTO>();
	
		List<Itens> todosItensCadastrados = itensService.todosItens();
		
		List<Rebelde> rebeldes = rebeldeRepo.rebeldesNaoTraidores();
		
		int qtdRebeldes = rebeldes.size();

		System.out.println(inventService.quantidadeDoItem(1L));
		
		for (Rebelde rebelde : rebeldes) {
			List<Itens> itens = rebelde.getInventario().getItens();
			for (Itens item : itens) {
				if(item.getId() == 1) { // Arma
					//qtdArma++;
				}else if(item.getId() == 2) { // Municao
					//qtdMunicao++;
				}else if(item.getId() == 3) { // Agua
					//qtdAgua++;
				}else if(item.getId() == 4) { // Comida
					//qtdComida++;
				}
			}
		}
		
		for (Itens item : todosItensCadastrados) {
			mediasItens.add(new RelatorioMediaItensDTO(item.getId(),item.getNomeItem(), 0, 0));
		}
		
		
		
	}
	*/
	
	
	
	
}
