package com.dam.Deck_Constructor_TFG.Modelo;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="deck")
public class Deck {
	@Id
	@Column(name="id_deck")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	public String nombre;
	
	public int nCartas; 
	
	public String formatoMTG;
	
	 @ManyToOne
	 @JoinColumn(name = "id_usuario")
	 private Usuario usuario;
	 
	 
	 @OneToMany(mappedBy = "deck", fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	 private List<Carta> deck_cards = new ArrayList<>();
	 
	 private boolean publico;
	 
	 public Deck() {
		 
	 }
	 public Deck(String nombre, String formatoMTG, boolean publico) {
		 this.nombre=nombre;
		 this.formatoMTG=formatoMTG;
		 this.publico=publico;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getnCartas() {
		return nCartas;
	}

	public void setnCartas(int nCartas) {
		this.nCartas = nCartas;
	}

	public String getFormatoMTG() {
		return formatoMTG;
	}

	public void setFormatoMTG(String formatoMTG) {
		this.formatoMTG = formatoMTG;
	}

	public List<Carta> getDeck_cards() {
		return deck_cards;
	}

	public void setDeck_cards(List<Carta> deck_cards) {
		this.deck_cards = deck_cards;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isPublico() {
		return publico;
	}


	public void setPublico(boolean publico) {
		this.publico = publico;
	}


	@Override
	public String toString() {
		return "Deck [id=" + id + ", nombre=" + nombre + ", nCartas=" + nCartas + ", formatoMTG=" + formatoMTG
				+ ", deck_cards=" + deck_cards + ", usuario=" + usuario + "]";
	}

}
