package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.dam.Deck_Constructor_TFG.Modelo.Carta;
import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;



public class RecuperarCartasDB {
	
	public static ArrayList<String> getDeckByName(String deckName){
		//String [] deck;
		
		Session sis = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "SELECT d FROM Deck d WHERE d.nombre = :deckName";
		Deck deck = sis.createQuery(hql, Deck.class)
		                      .setParameter("deckName", deckName)
		                      .getSingleResult();
		List<Carta> cartas = deck.getDeck_cards();
		
		ArrayList<String> cardNames = new ArrayList<String>();
		for(Carta c :cartas) {
			cardNames.add(c.getNombre());
		}
		
		sis.close();
		return cardNames;
	}

}
