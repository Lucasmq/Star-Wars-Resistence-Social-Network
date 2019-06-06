package com.rebeldes.resistencia.models.DTO;

import java.io.Serializable;

import com.rebeldes.resistencia.models.Inventario;

public class InventarioDeTrocaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Inventario inventario_1;
	private Inventario inventario_2;
	
	public InventarioDeTrocaDTO() {
	}
	
	public InventarioDeTrocaDTO(Inventario inventario_1, Inventario inventario_2) {
		super();
		this.inventario_1 = inventario_1;
		this.inventario_2 = inventario_2;
	}
	public Inventario getInventario_1() {
		return inventario_1;
	}
	public void setInventario_1(Inventario inventario_1) {
		this.inventario_1 = inventario_1;
	}
	public Inventario getInventario_2() {
		return inventario_2;
	}
	public void setInventario_2(Inventario inventario_2) {
		this.inventario_2 = inventario_2;
	}
}
