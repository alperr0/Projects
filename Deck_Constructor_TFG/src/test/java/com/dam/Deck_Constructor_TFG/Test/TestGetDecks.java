package com.dam.Deck_Constructor_TFG.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dam.Deck_Constructor_TFG.Modelo.Carta;
import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.RecuperarCartasDB;

class TestGetDecks {

	@Test
	void test() {
		var sis = HibernateUtil.getSessionFactory().openSession();
		sis.beginTransaction();
		Deck deck = new Deck("prueba", "Standard", true);

		// Instanciación de objetos Carta
		Carta carta1 = new Carta("Grindstone");
		Carta carta2 = new Carta("Black lotus");
		carta1.setDeck(deck);
		carta2.setDeck(deck);
		// Vinculación de cartas al deck
		List<Carta> cartas = new ArrayList<>();
		cartas.add(carta1);
		cartas.add(carta2);
		deck.setDeck_cards(cartas);

		sis.merge(deck);
		
		
		sis.getTransaction().commit();
		sis.close();
		
		ArrayList<String> a = RecuperarCartasDB.getDeckByName("prueba");
		System.out.println("CARTAS: ");
		for(String a1 :a) {
			System.out.println(a1);
		}
	}

}
