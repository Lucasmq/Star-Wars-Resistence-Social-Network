package com.rebeldes.resistencia.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Itens;
import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.InventarioRepository;
import com.rebeldes.resistencia.repository.ItensRepository;
import com.rebeldes.resistencia.repository.LocalizacaoRepository;
import com.rebeldes.resistencia.repository.RebeldeRepository;
import com.rebeldes.resistencia.services.InventarioService;
import com.rebeldes.resistencia.services.RebeldeService;

@Configuration // para o spring entender que é uma config
public class Instantiation implements CommandLineRunner {

	@Autowired
	private RebeldeRepository rebeldeRepository;
	
	@Autowired
	private RebeldeService rebeldeService;
	
	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	
	@Autowired
	private ItensRepository itensRepository;
	
	@Autowired
	private InventarioRepository inventarioRepository;
	
	@Autowired
	private InventarioService invetarioService;
	
	@Override
	public void run(String... args) throws Exception {
		List<Rebelde> rebeldes = new ArrayList<Rebelde>(rebeldeRepository.findAll());
		
		System.out.println(rebeldes.isEmpty());
		
		if(rebeldes.isEmpty()) {
			
			/*
			rebeldeRepository.deleteAll();
			localizacaoRepository.deleteAll();
			itensRepository.deleteAll();
			inventarioRepository.deleteAll();
			*/
			
			Localizacao l1 = new Localizacao(null, 123L, 321L, "Solar");
			Localizacao l2 = new Localizacao(null, 13L, 21L, "Patos");
			
			localizacaoRepository.saveAll(Arrays.asList(l1,l2)); // TODO verificar
	
			Itens item1 = new Itens(1L, "ARMA", 4L);
			Itens item2 = new Itens(2L, "MUNICAO", 3L);
			Itens item3 = new Itens(3L, "AGUA", 2L);
			Itens item4 = new Itens(4L, "COMIDA", 1L);
			
			List<Itens> listItens1 = new ArrayList<Itens>();
			List<Itens> listItens2 = new ArrayList<Itens>();
			
			//listItens.add((Itens) Arrays.asList(item1, item2, item3, item4));
			
			//listItens1.add(item1);
			listItens1.add(item1); // ARMA
			listItens1.add(item3); // AGUA
			listItens1.add(item4); // COMIDA
			
			listItens2.add(item1); // ARMA
			listItens2.add(item2); // MUNICAO
			listItens2.add(item2); // MUNICAO
			listItens2.add(item3); // AGUA
			listItens2.add(item4); // COMIDA
			
			itensRepository.saveAll(Arrays.asList(item1, item2, item3, item4));
			
			Inventario i1 = new Inventario(null, listItens1);
			Inventario i2 = new Inventario(null, listItens2);
			
			inventarioRepository.saveAll(Arrays.asList(i1,i2));
			
			
			Rebelde r1 = new Rebelde(null, "Lucas Queiroz", 24, "MASCULINO", 2L, false, l1, i1);
			Rebelde r2 = new Rebelde(null, "Ocraus", 18, "MASCULINO",(long)0, false, l2, i2);
			rebeldeRepository.save(r1);
			rebeldeRepository.save(r2);
		
		}else {
			
			Optional<Rebelde> rb1 = rebeldeRepository.findById(5L);
			Optional<Rebelde> rb2 = rebeldeRepository.findById(6L);
			
			List<Itens> its = rb1.get().getInventario().getItens();
			List<Itens> its2 = rb2.get().getInventario().getItens();
			
			//rb2.get().getInventario().removeItem(its.get(2));
			
			for (Itens item : its) {
				System.out.println(item.getNomeItem());
			}
			System.out.println("--------");
			for (Itens item : its2) {
				System.out.println(item.getNomeItem());
			}
			System.out.println("--------");
			
			//System.out.println(its2.contains(its.get(2)));
			//System.out.println(its.get(3).getNomeItem());
			
			//System.out.println(invetarioService.checaItensPresentesNoInventario(rb1.get().getInventario(), rb2.get().getInventario()));
			
			//System.out.println(rebeldeRepository.quantidadeTraidores());
			
			//for (Rebelde rebelde : rebeldeRepository.quantidadeTraidores()) {
			//	System.out.println(rebelde.getNome());
			//}
			//System.out.println(invetarioService.quantidadeDoItem(1L));
			//System.out.println(rebeldeService.pontosPerdidosTraidores());
			
			//System.out.println(rb2.get().getInventario().temItemNoInventario(its.get(2)));
			inventarioRepository.save(rb2.get().getInventario());
			rebeldeRepository.save(rb2.get());
			
		}
	}

}
