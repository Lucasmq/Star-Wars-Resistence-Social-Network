package com.rebeldes.resistencia.models.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.models.Rebelde;

public class RebeldeDTO {
	
	@NotBlank(message = "Nome não pode ser vazio!") // falta tratar a exibição das exceções
	@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
	private String nome;
	
	@NotNull(message= "Idade não pode ser vazia!")
	private Integer  idade;
	
	@NotBlank(message = "Nome não pode ser vazio!")
	private String genero;

	private Localizacao localizacao;

	private Inventario inventario;
	
	public RebeldeDTO() {
	}

	public RebeldeDTO(String nome, Integer idade, String genero, Localizacao localizacao, Inventario inventario) {
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
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
