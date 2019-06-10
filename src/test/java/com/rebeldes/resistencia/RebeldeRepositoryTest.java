package com.rebeldes.resistencia;

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
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.repository.ItensRepository;
import com.rebeldes.resistencia.repository.RebeldeRepository;
import com.rebeldes.resistencia.models.Rebelde.Genero;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RebeldeRepositoryTest {
	@Autowired
	private RebeldeRepository rebeldeRepository;
	
	@Autowired
	private ItensRepository itensRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();   // caso a expected não seja a esperada, acusa o erro
	
	@Test
	public void createShouldPersistData() {
		
		Itens item1 = new Itens(1L,"ARMA", 4L);
		Itens item2 = new Itens(2L,"MUNICAO", 3L);
		Itens item3 = new Itens(3L,"AGUA", 2L);
		
		this.itensRepository.saveAll(Arrays.asList(item1,item2,item3));
		
		List<Itens> itens = this.itensRepository.findAll(); // tira os 3 itens do repositorio 
		
		Inventario inventario = new Inventario(itens);
		
		Localizacao local = new Localizacao(888,777,"Patos");
		
		Rebelde rebelde = new Rebelde("Lucas Queiroz", 24, Genero.MASCULINO, local, inventario );
		this.rebeldeRepository.save(rebelde);
		Assertions.assertThat(rebelde.getId()).isNotNull();
		Assertions.assertThat(rebelde.getGenero()).isEqualTo(Genero.MASCULINO);
		Assertions.assertThat(rebelde.getInventario()).isEqualTo(inventario);  // testa se é o mesmo inventario
		Assertions.assertThat(rebelde.getLocalizacao()).isEqualTo(local);      // testa se é o mesmo local inserido
	}
}

// public Rebelde(String nome, Integer idade, String genero, Localizacao localizacao, Inventario inventario) {