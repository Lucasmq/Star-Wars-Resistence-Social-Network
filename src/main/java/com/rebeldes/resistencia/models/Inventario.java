package com.rebeldes.resistencia.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Inventario")
public class Inventario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Itens> itens = new ArrayList<Itens>();

	public Inventario() {
	}
	
	public Inventario(Long id, List<Itens> itens) {
		this.id = id;
		this.itens = itens;
	}
	
	public int quantidadeItens() {
		return this.itens.size();
	}
	
	public int quatidadeItemById(Long id) {
		int quantidade = 0;
		for (Itens itenPesquisado : itens) {
			if(itenPesquisado.getId() == id) {
				quantidade++;
			}
		}
		return quantidade;
	}
	
	public int pontuacaoTotalInventario() {
		int pontuacaoTotal = 0;
		for (Itens item : itens) {
			pontuacaoTotal += item.getPontos(); 
		}
		return pontuacaoTotal;
	}
	
	public boolean temItemNoInventario(Itens item) { // verifica se tem o item no inventario 
		return itens.contains(item);
	}
	

	public void removeItem(Itens item) { // TODO verificar se a lista não esta vazia e tratar
		this.itens.remove(item);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Itens> getItens() {
		return itens;
	}

	public void setItem(Itens item) {  // adiciona 1 item na lista de itens
		this.itens.add(item); 
	}
	
	public void setItens(List<Itens> itens) {
		this.itens = itens;
	}
}
