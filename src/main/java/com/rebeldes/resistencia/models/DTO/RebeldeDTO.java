package com.rebeldes.resistencia.models.DTO;

import com.rebeldes.resistencia.models.Inventario;
import com.rebeldes.resistencia.models.Localizacao;
import com.rebeldes.resistencia.models.Rebelde;

public class RebeldeDTO {
	
	private String nome;
	
	private int idade;
	
	private String genero;

	private Localizacao localizacao;

	private Inventario inventario;
	
	public RebeldeDTO() {
	}

	public RebeldeDTO(String nome, int idade, String genero, Localizacao localizacao, Inventario inventario) {
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
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
