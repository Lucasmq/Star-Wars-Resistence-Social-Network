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
	private Long id;
	
	private String nomeItem;
	
	private Long pontos;

	public Itens() {
		
	}
	
	public Itens(Long id, String nomeItem, Long pontos) {
		this.id = id;
		this.nomeItem = nomeItem;
		this.pontos = pontos;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itens other = (Itens) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
