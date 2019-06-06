package com.rebeldes.resistencia.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Itens;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.models.DTO.RelatorioMediaItensDTO;
import com.rebeldes.resistencia.repository.InventarioRepository;
import com.rebeldes.resistencia.repository.RebeldeRepository;

@Service
public class InventarioService {

		@Autowired
		private InventarioRepository inventRepo;
		
		@Autowired 
		private ItensService itensService;
		
		@Autowired
		private RebeldeRepository rebeldeRepo;
		
		public void saveInventario(Inventario inventario) {
			inventRepo.save(inventario);
		}
		
		public boolean pontuacaoEquivalenteEntreInventarios(Inventario inventario_1, Inventario inventario_2) {
			return inventario_1.pontuacaoTotalInventario() == inventario_2.pontuacaoTotalInventario();
		}
		
		public boolean checaItensPresentesNoInventario(Inventario inventario, Rebelde rb) {
			int quantidadeItemDoInventarioDeTroca = 0;
			int quantidadeItemDoRebelde = 0;

			for (Itens item : inventario.getItens()) {
				quantidadeItemDoRebelde 			= rb.getInventario().quatidadeItemById(item.getId());			  	// verifica a quantidade de itens no inventario do rebelde
				quantidadeItemDoInventarioDeTroca 	= inventario.quatidadeItemById(item.getId());						// verifica a quantidade de itens que o rebelde quer trocar
				if(quantidadeItemDoInventarioDeTroca > quantidadeItemDoRebelde) {										// verifica se o rebelde tem os itens que quer trocar no seu inventario
					return false;																						// caso não tenha, retorna false	
				}
			}
			return true;																								// caso o rebelde tenha os itens no inventario, retorna true
		}
		
		public boolean aptoParaTrocar(Rebelde rb1, Rebelde rb2, Inventario inventario_1, Inventario inventario_2) {
			if(checaItensPresentesNoInventario(inventario_1, rb1) && checaItensPresentesNoInventario(inventario_2, rb2) && !rb1.isTraidor() && !rb2.isTraidor() ) { // verifica se os rebeldes tem os itens no inventario e se algum é traidor
				if(pontuacaoEquivalenteEntreInventarios(inventario_1, inventario_2)) { 																				// caso os rebeldes tenham os itens no inventario, checa se os pontos são equivalentes para a troca
					return true;																																	// caso seja, retorna true
				}
			}
			return false;																																			// caso não passe em alguns dos ifs, retorna false, a troca não é permitida
		}
		
		public void trocarItensDoInventario(Rebelde rb1, Rebelde rb2, Inventario inventario_1, Inventario inventario_2) { 	// caso seja apto para troca, troca os itens entre os rebeldes
			for (Itens item : inventario_1.getItens()) {																	// coloca os itens do inventario1 no inventario do rebelde2
				rb2.getInventario().setItem(item);																			// coloca os itens do inventario1 no rebelde2
				rb1.getInventario().removeItem(item);																		// tira os itens do inventario1 do rebelde1
			}
			
			for (Itens item : inventario_2.getItens()) {        															// coloca os itens do inventario2 no inventario do rebelde1
				rb1.getInventario().setItem(item);																			// coloca os itens do inventario2 no rebelde1
				rb2.getInventario().removeItem(item);																		// tira os itens do inventario do rebelde2
			}	
			inventRepo.saveAll(Arrays.asList(rb1.getInventario(), rb2.getInventario()));								    // atualiza os inventarios dos rebeldes com os itens trocados
		}
		
		public List<RelatorioMediaItensDTO> mediaRecurso() {
			List<RelatorioMediaItensDTO> mediasItens = new ArrayList<RelatorioMediaItensDTO>();
			List<Itens> todosItensCadastrados = itensService.todosItens();
			List<Rebelde> rebeldes = rebeldeRepo.rebeldesNaoTraidores();			
			int qtdRebeldes = rebeldes.size();

			for (Itens item : todosItensCadastrados) {
				mediasItens.add(new RelatorioMediaItensDTO(item.getId(),item.getNomeItem(), 0, 0));
			}
			
			for (Rebelde rebelde : rebeldes) {
				if(!rebelde.isTraidor()) {
					List<Itens> itens = rebelde.getInventario().getItens();				
					for (Itens item : itens) {
						int index = (int) (long) item.getId() -1;
						mediasItens.get(index).addQuantidadeItem(1);
						mediasItens.get(index).setMediaItem((double) mediasItens.get(index).getQuantidadeItem()/(double) qtdRebeldes);
					}
				}
			}
			/*
			for (RelatorioMediaItensDTO media : mediasItens) {
				System.out.println(media.getNomeItem());
				System.out.print("Quantidade: ");
				System.out.println(media.getQuantidadeItem());
				System.out.print("Media por Rebelde: ");
				System.out.println(media.getMediaItem());
				System.out.println("--------------------");
			}
			 */
			return mediasItens;
		}
		
}
