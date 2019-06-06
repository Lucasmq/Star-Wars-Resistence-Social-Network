package com.rebeldes.resistencia.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.RebeldeRepository;

@Service
public class TraidoresService {
	
	@Autowired
	private RebeldeRepository rebeldeRepo;
	
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
