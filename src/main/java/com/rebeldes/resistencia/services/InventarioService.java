package com.rebeldes.resistencia.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Itens;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.InventarioRepository;
import com.rebeldes.resistencia.repository.ItensRepository;
import com.rebeldes.resistencia.repository.RebeldeRepository;

@Service
public class InventarioService {

		@Autowired
		private InventarioRepository inventRepo;
		
		@Autowired
		private ItensRepository itensRepo;
		
		@Autowired
		private RebeldeRepository rebeldeRepo;
		
		public void saveInventario(Inventario inventario) {
			inventRepo.save(inventario);
		}
		
		public boolean pontuacaoEquivalenteEntreInventarios(Inventario inventario_1, Inventario inventario_2) {
			return inventario_1.pontuacaoTotalInventario() == inventario_2.pontuacaoTotalInventario();
		}
		
		public boolean mesmosItensInventario(Inventario inventario_1, Inventario inventario_2) {
			List<Itens> itens = itensRepo.findAll(); // retorna todos os itens no bd
			int quantidadeItemInventario_1 = 0;
			int quantidadeItemInventario_2 = 0;
			for (Itens item : itens) {
				quantidadeItemInventario_1 = inventario_1.quatidadeItemById(item.getId());
				quantidadeItemInventario_2 = inventario_2.quatidadeItemById(item.getId());
				if(quantidadeItemInventario_1 != quantidadeItemInventario_2) {
					return false;																						// caso a quantidade seja diferente, não são iguais, retorna false
				}
			}
			return true; 																								// se acabar o loop, significa que tem todos os itens em mesma quantidade
		}
		
		public boolean checaItensPresentesNoInventario(Inventario inventario_1, Inventario inventario_2) {                      // checa se tem os itens suficientes no inventorio para a troca
			int quantidadeItemInventario = 0;
			int quantidadeItem = 0;
			//Inventario inventAux = new Inventario(null,itens);
			for (Itens item : inventario_1.getItens()) {
				quantidadeItemInventario = inventario_2.quatidadeItemById(item.getId());
				quantidadeItem 			 = inventario_2.quatidadeItemById(item.getId());
				if(quantidadeItemInventario < quantidadeItem) {
					return false;
				}
			}
			return true;
		}
		
		public boolean aptoParaTrocar(Rebelde rb1, Rebelde rb2, Inventario inventario_1, Inventario inventario_2) {
			if(checaItensPresentesNoInventario(inventario_1, inventario_2) && !rb1.isTraidor() && !rb2.isTraidor() ) {
				if(pontuacaoEquivalenteEntreInventarios(inventario_1, inventario_2)) {
					return true;
				}
			}else {
					return false;
			}
			return false;
		}
		
		public void trocarItensDoInventario(Rebelde rb1, Rebelde rb2, Inventario inventario_1, Inventario inventario_2) {
			for (Itens item : inventario_1.getItens()) {		// coloca os itens do inventario1 no inventario do rebelde2
				rb2.getInventario().setItem(item);				// coloca os itens do inventario1 no rebelde2
				rb1.getInventario().removeItem(item);			// tira os itens do inventario1 do rebelde1
			}
			
			for (Itens item : inventario_2.getItens()) {        // coloca os itens do inventario2 no inventario do rebelde1
				rb1.getInventario().setItem(item);				// coloca os itens do inventario2 no rebelde1
				rb2.getInventario().removeItem(item);			// tira os itens do inventario do rebelde2
			}	
			//rebeldeRepo.saveAll(Arrays.asList(rb1,rb2));		// salva os rebeldes com os novos repositorios
			inventRepo.saveAll(Arrays.asList(rb1.getInventario(), rb2.getInventario()));
		}
}
