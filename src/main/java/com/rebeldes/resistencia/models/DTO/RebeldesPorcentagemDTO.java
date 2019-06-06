package com.rebeldes.resistencia.models.DTO;

public class RebeldesPorcentagemDTO {
	private Double porcentagemDeRebeldes;
	
	public RebeldesPorcentagemDTO() {
	}

	public RebeldesPorcentagemDTO(Double porcentagemDeRebeldes) {
		super();
		this.porcentagemDeRebeldes = porcentagemDeRebeldes;
	}

	public Double getPorcentagemDeRebeldes() {
		return porcentagemDeRebeldes;
	}

	public void setPorcentagemDeRebeldes(Double porcentagemDeRebeldes) {
		this.porcentagemDeRebeldes = porcentagemDeRebeldes;
	}
	
	
}
