package com.rebeldes.resistencia.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITENS")
public class Itens implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nomeItem;
	
	private Long pontos;
	
	@ManyToOne
	private Inventario inventario;

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public Long getPontos() {
		return pontos;
	}

	public void setPontos(long pontos) {
		this.pontos = pontos;
	}
}
