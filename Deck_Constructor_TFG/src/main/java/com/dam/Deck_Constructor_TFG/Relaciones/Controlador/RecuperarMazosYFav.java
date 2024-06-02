package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;

import java.util.ArrayList;
import java.util.List;

import com.dam.Deck_Constructor_TFG.Modelo.Carta;
import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Modelo.Favoritos;
import com.dam.Deck_Constructor_TFG.Modelo.Usuario;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

public class RecuperarMazosYFav {
	
	public static ArrayList<String> recuperarMazosUsuario(String user) {
		var sis = HibernateUtil.getSessionFactory().openSession();
		
		
		String hql = "SELECT d FROM Deck d JOIN d.usuario u WHERE u.name = :userName";
		List<Deck> namesResult  = sis.createSelectionQuery(hql, Deck.class).setParameter("userName", user)
				.list();
		namesResult.stream().forEach(System.out::println);
	
		ArrayList<String> deckNames = new ArrayList<String>(); 
		for(Deck c : namesResult) {
			deckNames.add(c.getNombre());
		}
		for(String m : deckNames) {
			System.out.println("Mazo: "+ m);
		}
		if(deckNames.isEmpty()) {
			System.out.println("DECKS a NULL");
		}
		sis.close();
		return deckNames;
	}
	
	public static ArrayList<String> recuperarCartasFavoritos(String user){
		ArrayList<String> favNames = new ArrayList<String>();
		var sis = HibernateUtil.getSessionFactory().openSession();
		Favoritos favs;
		
		try {
			String hql = "SELECT f FROM Favoritos f JOIN f.usuario u WHERE u.name = :userName";
			favs = sis.createQuery(hql, Favoritos.class)
			        .setParameter("userName", user)
			        .getSingleResult();
			
			List<Carta> cards = favs.getFav_cards();
			for(Carta c: cards) {
				favNames.add(c.getNombre());
				System.out.println(c.getNombre());
			}
		} catch (Exception e) {
			
			favs = new Favoritos();
			String hql = "SELECT u FROM Usuario u WHERE u.name = :nombre";
			Usuario usuario = sis.createQuery(hql, Usuario.class)
				                      .setParameter("nombre", user)
				                      .getSingleResult();
			usuario.setFavs(favs);
			sis.merge(usuario);
			sis.getTransaction().commit();
		}
		
		
		sis.close();
		return favNames;
		
	}
}
 