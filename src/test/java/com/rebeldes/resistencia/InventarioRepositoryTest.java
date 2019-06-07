package com.rebeldes.resistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Itens;
import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.repository.InventarioRepository;
import com.rebeldes.resistencia.repository.ItensRepository;
import com.rebeldes.resistencia.repository.LocalizacaoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InventarioRepositoryTest {
	@Autowired
	private InventarioRepository inventarioRepository;
	
	@Autowired
	private ItensRepository itensRepository; // já foi testado
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();   // caso a expected não seja a esperada, acusa o erro
	
	@Test
	public void createShouldPersistData() { // testa se os itens cadastrados estão corretos
		
		Itens item1 = new Itens(1L,"ARMA", 4L);
		Itens item2 = new Itens(2L,"MUNICAO", 3L);
		Itens item3 = new Itens(3L,"AGUA", 2L);
		
		this.itensRepository.saveAll(Arrays.asList(item1,item2,item3));  // salvando os itens no repositorio dos itens
		
		
		List<Itens> itens = this.itensRepository.findAll(); // tira os 3 itens do repositorio 
		Assertions.assertThat(itens.size()).isEqualTo(3);  // testa se é 3 mesmo o numero de itens no repositorio
		
		Inventario inventario = new Inventario(itens);
		this.inventarioRepository.save(inventario);
		
		Assertions.assertThat(inventario.getId()).isNotNull(); // testa se o id foi gerado como esperado
		Assertions.assertThat(inventario.pontuacaoTotalInventario()).isEqualTo(9L); // foi cadastrado 3 itens com o total de 9 pontos, então é testado
	}
}
