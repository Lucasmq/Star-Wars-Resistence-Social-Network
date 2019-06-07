package com.rebeldes.resistencia;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rebeldes.resistencia.models.Itens;
import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.repository.ItensRepository;
import com.rebeldes.resistencia.repository.LocalizacaoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItensRepositoryTest {
	@Autowired
	private ItensRepository itensRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();   // caso a expected não seja a esperada, acusa o erro
	
	@Test
	public void createShouldPersistData() { 							// testa se os itens cadastrados estão corretos
		Itens item = new Itens(1L,"ARMA", 4L);
		this.itensRepository.save(item);
		Assertions.assertThat(item.getId()).isEqualTo(1L); 				// testa se o id foi mesmo declarado
		Assertions.assertThat(item.getNomeItem()).isEqualTo("ARMA");
		Assertions.assertThat(item.getPontos()).isEqualTo(4L);
	}
}
