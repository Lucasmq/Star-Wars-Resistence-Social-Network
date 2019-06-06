package com.rebeldes.resistencia.models.DTO;

public class RelatorioMediaItensDTO {

	private Long id;
	private String nomeItem;
	private int quantidadeItem;
	private double mediaItem;
	
	public RelatorioMediaItensDTO() {
	}
	
	public RelatorioMediaItensDTO(Long id, String nomeItem,int quantidadeItem, double mediaItem) {
		this.id = id;
		this.nomeItem = nomeItem;
		this.quantidadeItem = quantidadeItem;
		this.mediaItem = mediaItem;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public int getQuantidadeItem() {
		return quantidadeItem;
	}

	public void setQuantidadeItem(int quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}

	public String getNomeItem() {
		return nomeItem;
	}
	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}
	public double getMediaItem() {
		return mediaItem;
	}
	public void setMediaItem(double mediaItem) {
		this.mediaItem = mediaItem;
	}

}
