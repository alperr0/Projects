package com.dam.Deck_Constructor_TFG.Modelo;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="carta")
public class Carta {
	@Id
	@Column(name="id_carta")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	public String nombre;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_deck")
	private Deck deck;
	
	@ManyToOne
    @JoinColumn(name = "id_favs")
    private Favoritos favoritos;
	
	public Carta() {
		
	}

	public Carta(String nombre) {
		this.nombre = nombre;
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

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Favoritos getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(Favoritos favoritos) {
		this.favoritos = favoritos;
	}
	
	
}
