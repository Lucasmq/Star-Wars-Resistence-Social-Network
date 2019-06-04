package com.rebeldes.resistencia.models;

import java.io.Serializable;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="REBELDE")
public class Rebelde  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private int idade;
	
	private String genero;
	
	private Long votosTraidor;
	
	private boolean traidor;
	
	@OneToOne (mappedBy = "rebelde")//, cascade = CascadeType.ALL)
	private Localizacao localizacao;

	@OneToOne(mappedBy = "rebelde")
	private Inventario inventario;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Long getVotosTraidor() {
		return votosTraidor;
	}

	public void setVotosTraidor(long votosTraidor) {
		this.votosTraidor = votosTraidor;
	}

	public boolean isTraidor() {
		return traidor;
	}

	public void setTraidor(boolean traidor) {
		this.traidor = traidor;
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
