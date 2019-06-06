package com.rebeldes.resistencia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Itens;
import com.rebeldes.resistencia.repository.ItensRepository;

@Service
public class ItensService {
	@Autowired
	private ItensRepository repo;
	
	public List<Itens> todosItens(){
		return repo.findAll();
	}
	
}
