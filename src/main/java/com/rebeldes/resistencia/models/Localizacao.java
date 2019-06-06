package com.rebeldes.resistencia.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="LOCALIZACAO")
public class Localizacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; // usar esse id compartilhado com o id de rebelde
	
	@NotNull(message= "Latitude tem que ser informada!")
	private Integer latitude;
	
	@NotNull(message= "Longitude tem que ser informada!")
	private Integer longitude;
	
	@NotBlank (message= "Informe a galaxia da qual a base faz parte")
	private String nomeGalaxia;

	public Localizacao() {
		
	}

	public Localizacao(Long id, Integer latitude, Integer longitude, String nomeGalaxia) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.nomeGalaxia = nomeGalaxia;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public String getNomeGalaxia() {
		return nomeGalaxia;
	}

	public void setNomeGalaxia(String nomeGalaxia) {
		this.nomeGalaxia = nomeGalaxia;
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
		Localizacao other = (Localizacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
