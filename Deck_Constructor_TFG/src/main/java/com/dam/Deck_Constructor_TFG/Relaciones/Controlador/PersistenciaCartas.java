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
		Carta c = new Carta(nomCarta);
		c.setDeck(deck);
		deck.getDeck_cards().add(c);
		
		sis.beginTransaction();
		sis.merge(c);
		sis.merge(deck);
		sis.getTransaction().commit();
		sis.close();
		SwingUtilities.invokeLater(() -> {
            ToastDialog dialog = new ToastDialog("Carta '"+nomCarta+"' añadida a '"+deckName+"'", 1000);
            dialog.mostrarToast();
        });
	}
	public static void addCartaFavoritos(String nomCarta, String user) {
		var sis = HibernateUtil.getSessionFactory().openSession();
		
		Favoritos favs;
		Carta c;
		Usuario usuario = new Usuario();
		boolean aux = false;
		try {
			String hql = "SELECT f FROM Favoritos f JOIN f.usuario u WHERE u.name = :userName";
			favs = sis.createQuery(hql, Favoritos.class)
			        .setParameter("userName", user)
			        .getSingleResult();
			c = new Carta(nomCarta);
			c.setFavoritos(favs);
			favs.getFav_cards().add(c);
		} catch (Exception e) {
			String hql = "SELECT u FROM Usuario u WHERE u.name = :nombre";
			usuario = sis.createQuery(hql, Usuario.class)
				                      .setParameter("nombre", user)
				                      .getSingleResult();
			favs = new Favoritos();
			c = new Carta(nomCarta);
			c.setFavoritos(favs);
			favs.getFav_cards().add(c);
			usuario.setFavs(favs);
			aux = true;
		}
		
		sis.beginTransaction();
		sis.merge(c);
		sis.merge(favs);
		if (aux) sis.merge(usuario);
		sis.getTransaction().commit();
		sis.close();
		SwingUtilities.invokeLater(() -> {
            ToastDialog dialog = new ToastDialog("Carta '"+nomCarta+"' añadida a Favoritos", 1000);
            dialog.mostrarToast();
        });
	}
}
