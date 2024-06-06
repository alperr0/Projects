package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

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

		String hql = "SELECT f FROM Favoritos f JOIN f.usuario u WHERE u.name = :userName";
		favs = sis.createQuery(hql, Favoritos.class)
		        .setParameter("userName", user)
		        .getSingleResult();
		
		List<Carta> cards = favs.getFav_cards();
		for(Carta c: cards) {
			favNames.add(c.getNombre());
			System.out.println(c.getNombre());
		}

		sis.close();
		
		return favNames;
		
	}
	public static ArrayList<String[]> recuperarCartasMazo(String user, String nameMazo){
		ArrayList<String[]> deckCardNames = new ArrayList<String[]>();
		var sis = HibernateUtil.getSessionFactory().openSession();
		
	
			String hql = "SELECT d FROM Deck d JOIN d.usuario u WHERE u.name = :userName AND d.nombre = :nameMazo";
			Deck d = sis.createQuery(hql, Deck.class)
			        .setParameter("userName", user) .setParameter("nameMazo", nameMazo)
			        .getSingleResult();
			
			List<Carta> cards = d.getDeck_cards();
			for(Carta c: cards) {
				deckCardNames.add(new String[]{c.getNombre(), Short.toString(c.getnCopias())});
				System.out.println(c.getNombre());
			}
		
		sis.close();
		
		return deckCardNames;
		
	}
	public static void borrarCartaMazo(String user, String nameMazo, String nameCarta){
		var sis = HibernateUtil.getSessionFactory().openSession();

	    try {
	    	sis.beginTransaction();

	        // Consulta para obtener el mazo específico del usuario
	        String hql = "SELECT d FROM Deck d JOIN d.usuario u WHERE u.name = :userName AND d.nombre = :nameMazo";
	        Deck deck = sis.createQuery(hql, Deck.class)
	                .setParameter("userName", user)
	                .setParameter("nameMazo", nameMazo)
	                .getSingleResult();

	        // Filtrar las cartas en el mazo para encontrar la carta específica
	        List<Carta> cards = deck.getDeck_cards();
	        Carta cardToRemove = null;
	        for (Carta card : cards) {
	            if (card.getNombre().equals(nameCarta)) {
	                cardToRemove = card;
	                break;
	            }
	        }

	        // Si se encuentra la carta, se elimina del mazo
	        if (cardToRemove != null) {
	            cards.remove(cardToRemove);
	            sis.remove(cardToRemove);
	        } else {
	            System.out.println("Carta no encontrada en el mazo");
	        }

	        // Guardar los cambios
	        sis.merge(deck);
	        sis.getTransaction().commit();
	    } catch (Exception e) {
	        if (sis.getTransaction() != null) {
	            sis.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        sis.close();
	    }
		
	}
	public static void borrarCartaFavs(String user, String nameCarta){
		 var sis = HibernateUtil.getSessionFactory().openSession();

		    try {
		        sis.beginTransaction();

		        // Consulta para obtener los favoritos específicos del usuario
		        String hql = "SELECT f FROM Favoritos f JOIN f.usuario u WHERE u.name = :userName";
		        Favoritos favs = sis.createQuery(hql, Favoritos.class)
		                .setParameter("userName", user)
		                .getSingleResult();

		        // Filtrar las cartas en favoritos para encontrar la carta específica
		        List<Carta> cards = favs.getFav_cards();
		        Carta cardToRemove = null;
		        for (Carta card : cards) {
		            if (card.getNombre().equals(nameCarta)) {
		                cardToRemove = card;
		                break;
		            }
		        }

		        // Si se encuentra la carta, se elimina de favoritos
		        if (cardToRemove != null) {
		            // Eliminar la carta de la colección del favorito
		            cards.remove(cardToRemove);
		            // También eliminar la carta de la base de datos
		            sis.remove(cardToRemove);
		            // Actualizar los favoritos en la sesión
		            sis.merge(favs);
		        } else {
		            System.out.println("Carta no encontrada en los favoritos");
		        }

		        // Guardar los cambios
		        sis.getTransaction().commit();
		    } catch (Exception e) {
		        if (sis.getTransaction() != null) {
		            sis.getTransaction().rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        sis.close();
		    }
		
	}
	public static void initFavs(String user){
		try {
			var sis = HibernateUtil.getSessionFactory().openSession();
			Favoritos favs = null;
			sis.beginTransaction();

			String hql = "SELECT u FROM Usuario u WHERE u.name = :nombre";
			Usuario usuario = sis.createQuery(hql, Usuario.class)
			                    .setParameter("nombre", user)
			                    .getSingleResult();

			if (usuario.getFavs() == null) {
			    favs = new Favoritos();
			    usuario.setFavs(favs);
			    sis.merge(usuario);
			} else {
			    favs = usuario.getFavs();
			}

			sis.getTransaction().commit();
			sis.close();
		} catch (HibernateException e) {
			
		}
		
	}
}
 