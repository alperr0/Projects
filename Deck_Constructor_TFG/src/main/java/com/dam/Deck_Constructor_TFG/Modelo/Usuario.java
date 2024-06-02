package com.dam.Deck_Constructor_TFG.Modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name="usuario")
public class Usuario {
	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre")
	private String name;
	
	private String pass;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE)
	private List<Deck> decks;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_favs")
    private Favoritos favs;
	
	@OneToMany(mappedBy = "usuario")
	private List<Amigo> amigos;
	
	public Usuario() {
		
	}

	public Usuario(String name, String pass) {
		this.name = name;
		this.pass = pass;
		this.decks = new ArrayList<>();
		this.amigos =new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Deck> getDecks() {
		return decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}

	public Favoritos getFavs() {
		return favs;
	}

	public void setFavs(Favoritos favs) {
		this.favs = favs;
	}
	
	
	public List<Amigo> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Amigo> amigos) {
		this.amigos = amigos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", pass=" + pass + ", decks=" + decks + ", favs=" + favs + "]";
	}
	
	
}
