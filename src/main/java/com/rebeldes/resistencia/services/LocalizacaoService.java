package com.rebeldes.resistencia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.LocalizacaoRepository;
import com.rebeldes.resistencia.repository.RebeldeRepository;

@Service
public class LocalizacaoService {

	@Autowired
	private LocalizacaoRepository localRepo;
	
	@Autowired
	private RebeldeService rebelService;
	
	public Localizacao findLocalizacaoByRebeldeId(Long id) {
		Rebelde rb = rebelService.findById(id);
		Optional<Localizacao> local = localRepo.findById(rb.getLocalizacao().getId());
		return local.get();
	}
	
	public Localizacao updateLocalizacaoByRebeldeId(Long id,Localizacao local) {
		Rebelde rb = rebelService.findById(id);
		localRepo.save(local);
		rb.setLocalizacao(local);
		rebelService.salvarRebelde(rb);
		return local;
	}
	
	public void saveLocalizacao(Localizacao local) {
		localRepo.save(local);
	}
	
	
}
