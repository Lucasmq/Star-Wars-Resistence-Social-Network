package com.rebeldes.resistencia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.LocalizacaoRepository;
import com.rebeldes.resistencia.services.exception.ObjectNotFoundException;

@Service
public class LocalizacaoService {

	@Autowired
	private LocalizacaoRepository localRepo;
	
	@Autowired
	private RebeldeService rebelService;
	
	public Localizacao findLocalizacaoByRebeldeId(Long id) {
		Rebelde rb = rebelService.findById(id);
		Optional<Localizacao> local = localRepo.findById(rb.getLocalizacao().getId());
		return local.orElseThrow(() -> new ObjectNotFoundException("Localização não encontrada"));
	}
	
	public Localizacao updateLocalizacaoByRebeldeId(Long id,Localizacao local) {
		Rebelde rb = rebelService.findById(id);
		Optional<Localizacao> lc = localRepo.findById(rb.getLocalizacao().getId());
		
		lc.get().setLatitude(local.getLatitude());
		lc.get().setLongitude(local.getLongitude());
		lc.get().setNomeGalaxia(local.getNomeGalaxia());
		
		localRepo.save(lc.get());
		return lc.get();
	}
	
	public void saveLocalizacao(Localizacao local) {
		localRepo.save(local);
	}
	
	
}
