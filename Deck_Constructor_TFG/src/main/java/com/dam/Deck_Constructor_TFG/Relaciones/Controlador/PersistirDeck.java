package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;

import org.hibernate.Session;

import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Modelo.Usuario;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;


public class PersistirDeck {
	public static boolean persistirDeck(String nombre, String user){
		boolean persistirCartas= false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
	        session.beginTransaction();

	        // Verificar si el mazo ya existe para el usuario
	       
	         Deck existe = session.createQuery("SELECT d FROM Deck d WHERE d.nombre = :nombre AND d.usuario.name = :user", Deck.class)
                    .setParameter("nombre", nombre)
                    .setParameter("user", user)
                    .getSingleResult();

	        if (existe == null) {
	            // El mazo no existe, se crea uno nuevo
	            Deck nuevoDeck = new Deck(nombre);

	            // Obtener el usuario
	            Usuario usuario = session.createQuery(
	                "SELECT u FROM Usuario u WHERE u.name = :nombre", 
	                Usuario.class
	            )
	            .setParameter("nombre", user)
	            .getSingleResult();

	            // Asignar el usuario al mazo y viceversa
	            nuevoDeck.setUsuario(usuario);
	            usuario.getDecks().add(nuevoDeck);

	            // Persistir el mazo y actualizar el usuario
	            session.persist(nuevoDeck);
	            session.merge(usuario);

	            session.getTransaction().commit();
	            persistirCartas= true;
	        } 

	    } catch (Exception e) {
	        if (session.getTransaction() != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	        
	    }
		return persistirCartas;
	}
	
}
