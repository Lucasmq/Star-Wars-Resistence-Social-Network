package com.rebeldes.resistencia.models.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.models.Rebelde;
import com.rebeldes.resistencia.models.Rebelde.Genero;

public class RebeldeDTO {
	
	@NotBlank(message = "Nome não pode ser vazio!") // falta tratar a exibição das exceções
	@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
	private String nome;
	
	@NotNull(message= "Idade não pode ser vazia!")
	private Integer  idade;
	
	//@NotBlank(message = "Nome não pode ser vazio!")
	@NotNull
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@NotNull
	private Localizacao localizacao;
	
	@NotNull
	private Inventario inventario;
	
	public RebeldeDTO() {
	}

	public RebeldeDTO(String nome, Integer idade, Genero genero, Localizacao localizacao, Inventario inventario) {
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.localizacao = localizacao;
		this.inventario = inventario;
	}
	
	public Rebelde tranformaParaRebelde() {
		return new Rebelde(nome, idade, genero, localizacao, inventario);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	
}
