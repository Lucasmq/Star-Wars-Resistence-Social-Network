package com.rebeldes.resistencia.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

@Configuration // para o spring entender que é uma config
public class Instantiation implements CommandLineRunner {

	@Autowired
	private RebeldeRepository rebeldeRepository;
	
	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	
	@Autowired
	private ItensRepository itensRepository;
	
	@Autowired
	private InventarioRepository inventarioRepository;
	
	@Override
	public void run(String... args) throws Exception {
		List<Rebelde> rebeldes = new ArrayList<Rebelde>(rebeldeRepository.findAll());

		if(rebeldes.isEmpty()) {
		
			Localizacao l1 = new Localizacao(null, 123L, 321L, "Solar");
			Localizacao l2 = new Localizacao(null, 13L, 21L, "Patos");
			
			localizacaoRepository.saveAll(Arrays.asList(l1,l2));
	
			Itens item1 = new Itens(1L, "ARMA", 4L);
			Itens item2 = new Itens(2L, "MUNICAO", 3L);
			Itens item3 = new Itens(3L, "AGUA", 2L);
			Itens item4 = new Itens(4L, "COMIDA", 1L);
			
			List<Itens> listItens1 = new ArrayList<Itens>();
			List<Itens> listItens2 = new ArrayList<Itens>();
			
			listItens1.add(item1); // ARMA
			listItens1.add(item3); // AGUA
			listItens1.add(item4); // COMIDA
			
			listItens2.add(item1); // ARMA
			listItens2.add(item2); // MUNICAO
			listItens2.add(item2); // MUNICAO
			listItens2.add(item2); // MUNICAO
			listItens2.add(item2); // MUNICAO
			listItens2.add(item2); // MUNICAO
			listItens2.add(item2); // MUNICAO
			listItens2.add(item2); // MUNICAO
			listItens2.add(item3); // AGUA
			listItens2.add(item4); // COMIDA
			
			itensRepository.saveAll(Arrays.asList(item1, item2, item3, item4));
			
			Inventario i1 = new Inventario(null, listItens1);
			Inventario i2 = new Inventario(null, listItens2);
			
			inventarioRepository.saveAll(Arrays.asList(i1,i2));
			
			
			Rebelde r1 = new Rebelde(null, "Lucas Queiroz", 24, "MASCULINO", false, l1, i1);
			Rebelde r2 = new Rebelde(null, "Ocraus", 18, "MASCULINO", false, l2, i2);
			rebeldeRepository.save(r1);
			rebeldeRepository.save(r2);
		
		}
	}

}
