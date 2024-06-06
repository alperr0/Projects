package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;



import javax.swing.SwingUtilities;

import com.dam.Deck_Constructor_TFG.Modelo.Carta;
import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Modelo.Favoritos;
import com.dam.Deck_Constructor_TFG.Modelo.Usuario;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;
import com.dam.Deck_Constructor_TFG.Vista.ToastDialog;

public class PersistenciaCartas {
	public static void addCartaMazo(String nomCarta, String deckName) {
		var sis = HibernateUtil.getSessionFactory().openSession();

		String hql = "SELECT d FROM Deck d WHERE d.nombre = :deckName";
		Deck deck = sis.createQuery(hql, Deck.class)
		        .setParameter("deckName", deckName)
		        .getSingleResult();

		boolean cartaExiste = deck.getDeck_cards().stream()
		                          .anyMatch(card -> card.getNombre().equals(nomCarta));

		if (cartaExiste) {
			SwingUtilities.invokeLater(() -> {
	            ToastDialog dialog = new ToastDialog("'"+nomCarta+"' ya fue añadida a '"+deckName+"'", 1000);
	            dialog.mostrarToast();
	        });
		} else {
		    // Crear y añadir la nueva carta al mazo
		    Carta c = new Carta(nomCarta);
		    c.setnCopias((short) 1);
		    c.setDeck(deck);
		    deck.getDeck_cards().add(c);

		    sis.beginTransaction();
		    sis.merge(c);
		    sis.merge(deck);
		    sis.getTransaction().commit();
		    SwingUtilities.invokeLater(() -> {
	            ToastDialog dialog = new ToastDialog("Carta '"+nomCarta+"' añadida a '"+deckName+"'", 1000);
	            dialog.mostrarToast();
	        });
		}

		sis.close();
		
	}
	
	public static void addCartaFavoritos(String nomCarta, String user) {
		var sis = HibernateUtil.getSessionFactory().openSession();
		Favoritos favs;
		Carta c;
		sis.beginTransaction();

		String hql = "SELECT f FROM Favoritos f JOIN f.usuario u WHERE u.name = :userName";
		favs = sis.createQuery(hql, Favoritos.class)
		        .setParameter("userName", user)
		        .getSingleResult();
		
		boolean cartaExiste = favs.getFav_cards().stream()
		                          .anyMatch(card -> card.getNombre().equals(nomCarta));
		if (cartaExiste) {
			SwingUtilities.invokeLater(() -> {
	            ToastDialog dialog = new ToastDialog("'"+nomCarta+"' ya estaba añadida", 1000);
	            dialog.mostrarToast();
	        });
		} else {
		    c = new Carta(nomCarta);
		    c.setFavoritos(favs);
		    favs.getFav_cards().add(c);
		    sis.merge(c);
		    sis.merge(favs);
		    sis.getTransaction().commit();
		    SwingUtilities.invokeLater(() -> {
	            ToastDialog dialog = new ToastDialog("Carta '"+nomCarta+"' añadida a Favoritos", 1000);
	            dialog.mostrarToast();
	        });
		}

		
		sis.close();
		
	}
}
