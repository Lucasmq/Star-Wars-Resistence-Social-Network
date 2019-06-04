package com.rebeldes.resistencia.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="LOCALIZACAO")
public class Localizacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id; // usar esse id compartilhado com o id de rebelde
	
	private Long latitude;
	
	private Long longitude;
	
	private String nomeGalaxia;
	
	@OneToOne
	@MapsId
	private Rebelde rebelde;
	

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public String getNomeGalaxia() {
		return nomeGalaxia;
	}

	public void setNomeGalaxia(String nomeGalaxia) {
		this.nomeGalaxia = nomeGalaxia;
	}

	public Rebelde getRebelde() {
		return rebelde;
	}

	public void setRebelde(Rebelde rebelde) {
		this.rebelde = rebelde;
	}
	
}
