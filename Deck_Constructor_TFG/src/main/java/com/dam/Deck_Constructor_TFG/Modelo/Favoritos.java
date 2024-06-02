package com.dam.Deck_Constructor_TFG.Modelo;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="favoritos")
public class Favoritos {
	@Id
	@Column(name="id_favs")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@OneToOne(mappedBy = "favs")
	@JoinColumn(name = "id_usuario")
    private Usuario usuario;
	
	@OneToMany(mappedBy = "favoritos")
    private List<Carta> fav_cards = new ArrayList<>();
	
	public Favoritos() {
		
	}

	public Favoritos(List<Carta> fav_cards) {
		this.fav_cards = fav_cards;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Carta> getFav_cards() {
		return fav_cards;
	}

	public void setFav_cards(List<Carta> fav_cards) {
		this.fav_cards = fav_cards;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean addCard(Carta card) {
		if(this.fav_cards.contains(card)) {
			return false;
		}else {
			this.fav_cards.add(card);
			return true;
		}
	}
	
	public boolean removeCard(Carta card) {
		if(this.fav_cards.contains(card)) {
			this.fav_cards.remove(card);
			return true;
		}else {
			this.fav_cards.add(card);
			return true;
		}
	}

	@Override
	public String toString() {
		return "Favoritos [id=" + id + ", fav_cards=" + fav_cards + ", usuario=" + usuario + "]";
	}
	
	

	
	
}
 