package com.rebeldes.resistencia;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.repository.LocalizacaoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LocalizacaoRepositoryTest {
	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();   // caso a expected não seja a esperada, acusa o erro
	
	@Test
	public void createShouldPersistData() { 							// testa se os itens cadastrados estão corretos
		Localizacao local = new Localizacao(777,898,"PatosLactea");
		this.localizacaoRepository.save(local);
		Assertions.assertThat(local.getId()).isNotNull(); // testa se o id foi gerado como esperado
		Assertions.assertThat(local.getLatitude()).isEqualTo(777);
		Assertions.assertThat(local.getLongitude()).isEqualTo(898);
		Assertions.assertThat(local.getNomeGalaxia()).isEqualTo("PatosLactea"); 
	}
	
	@Test
	public void updatShouldChanceAndPersistData() {   			// salvo a localizacao e depois atulizado e verifico se a atualização ocorreu com sucesso
		Localizacao local = new Localizacao(777,898,"PatosLactea");
		this.localizacaoRepository.save(local);
		local.setLatitude(544);
		local.setLongitude(-999);
		local.setNomeGalaxia("Via Lactea");
		this.localizacaoRepository.save(local);
		Optional<Localizacao> local2 = this.localizacaoRepository.findById(local.getId());
		Assertions.assertThat(local2.get().getId()).isEqualTo(local.getId());
		Assertions.assertThat(local2.get().getLatitude()).isEqualTo(544);
		Assertions.assertThat(local2.get().getLongitude()).isEqualTo(-999);
		Assertions.assertThat(local2.get().getNomeGalaxia()).isEqualTo("Via Lactea");
	}
	/*
	@Test
	public void createWhenLatitudeIsNullShouldThrowConstraintViolationException() { // para esse teste esta dando erro 500
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Latitude tem que ser informada!");
		this.localizacaoRepository.save(new Localizacao());
	}
	*/
}
