package com.rebeldes.resistencia.models.DTO;

public class TraidoresPorcentagemDTO {

		private Double PorcentagemDeTraidores;
		
		public TraidoresPorcentagemDTO() {
			
		}

		public TraidoresPorcentagemDTO(Double porcentagemDeTraidores) {
			super();
			PorcentagemDeTraidores = porcentagemDeTraidores;
		}

		public Double getPorcentagemDeTraidores() {
			return PorcentagemDeTraidores;
		}

		public void setPorcentagemDeTraidores(Double porcentagemDeTraidores) {
			PorcentagemDeTraidores = porcentagemDeTraidores;
		}

}
