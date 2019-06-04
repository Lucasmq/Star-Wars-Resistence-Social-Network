package com.rebeldes.resistencia.models;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Inventario")
public class Inventario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
//	private Long itemId; // id o do item na tabela ITEM 
	
	private Long quantidade;
	
	@OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL)
	//@JoinColumn(name = "fk_itens")
	private List<Itens> itens = new ArrayList<Itens>();

	//@ManyToMany
	//private Set<Rebelde> rebeldes = new HashSet<Rebelde>(); // id referente ao rebelde a quem pertence o item
	@OneToOne
	private Rebelde rebelde;
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

/*	public Long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
*/
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}


	public List<Itens> getItens() {
		return itens;
	}

	public void setItens(List<Itens> itens) {
		this.itens = itens;
	}
	
	public Rebelde getRebelde() {
		return rebelde;
	}

	public void setRebelde(Rebelde rebelde) {
		this.rebelde = rebelde;
	}
}
