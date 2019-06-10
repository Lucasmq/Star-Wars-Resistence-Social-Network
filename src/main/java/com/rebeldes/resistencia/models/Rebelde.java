package com.rebeldes.resistencia.models;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="REBELDE")
@SequenceGenerator(name = "GEN_SEQ_REBELDE", sequenceName = "SEQ_REBELDE", allocationSize = 10)
public class Rebelde  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "GEN_SEQ_REBELDE") // gera as sequencias para os ids do rebelde
	private Long id;
	
	private String nome;
	
	private Integer idade;
	
	//private String genero;			// aqui seria melhor utilizar ENUM
	public enum Genero {
		 MASCULINO,
		 FEMININO;
	}
	
	@Enumerated(EnumType.STRING)
    private Genero genero;
	
	private Integer votosTraidor;
	
	private boolean traidor;
	
	@OneToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_localizacao")
	private Localizacao localizacao;

	@OneToOne//(cascade = CascadeType.ALL) // ao ser apagado um rebelde, apaga seu inventario junto
	@JoinColumn(name = "fk_inventario")	
	private Inventario inventario;

	public Rebelde() {
		this.votosTraidor = 0;
		this.traidor = false;
	}
	
	public Rebelde(String nome, Integer idade, Genero genero, Localizacao localizacao, Inventario inventario) {
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.votosTraidor = 0;
		this.localizacao = localizacao;
		this.inventario = inventario;
	}
	
	public Rebelde(Long id, String nome, Integer idade, Genero genero, boolean traidor,
			Localizacao localizacao, Inventario inventario) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.votosTraidor = 0;
		this.traidor = traidor;
		this.localizacao = localizacao;
		this.inventario = inventario;
	}

	public void addVotoTraidor() { // adiciona voto ao rebelde sobre traição, e caso o rebelde tenha 3 votos ou mais, é classificado como traidor
		this.votosTraidor++;
		if(quantidadeVotos() >= 3 && !checarTraicao()) {
			virouTraidor();
		}
	}
	
	public Integer quantidadeVotos() {
		return this.votosTraidor;
	}
	
	public Boolean checarTraicao() { // checa se é traidor
		return this.traidor;
	}
	
	public void virouTraidor() {  // marca o rebelde como traidor
		this.traidor = true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Integer getVotosTraidor() {
		return votosTraidor;
	}

	public void setVotosTraidor(Integer votosTraidor) {
		this.votosTraidor = votosTraidor;
	}

	public boolean isTraidor() {
		return traidor;
	}

	public void setTraidor(boolean traidor) {
		this.traidor = traidor;
	}
	
	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	
	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	
	

	@Override
	public String toString() {
		return "Rebelde [id=" + id + ", nome=" + nome + ", idade=" + idade + ", genero=" + genero + ", votosTraidor="
				+ votosTraidor + ", traidor=" + traidor + ", localizacao=" + localizacao + ", inventario=" + inventario
				+ "]";
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
		Rebelde other = (Rebelde) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
