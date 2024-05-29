package com.dam.Deck_Constructor_TFG.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "amigo")
public class Amigo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_amistad")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_amigo")
    private Usuario amigo;
    
    public Amigo() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getAmigo() {
		return amigo;
	}

	public void setAmigo(Usuario amigo) {
		this.amigo = amigo;
	}

	@Override
	public String toString() {
		return "Amigo [id=" + id + ", usuario=" + usuario + ", amigo=" + amigo + "]";
	}
    
    
}
