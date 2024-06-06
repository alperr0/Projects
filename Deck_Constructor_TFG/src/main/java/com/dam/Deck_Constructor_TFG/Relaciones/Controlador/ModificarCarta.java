package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;

import com.dam.Deck_Constructor_TFG.Modelo.Carta;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

public class ModificarCarta {
	public static void cambiarCopiasCarta(String user, String nomDeck, String nomCarta, short nCopias) {
		System.out.println("Entra en modificar carta");
		var sis = HibernateUtil.getSessionFactory().openSession();
	
		String hql = "SELECT c FROM Carta c JOIN c.deck d JOIN d.usuario u " +
	             "WHERE u.name = :userName AND d.nombre = :nameMazo AND c.nombre = :nombreCarta";
		Carta c = sis.createQuery(hql, Carta.class)
			                      .setParameter("userName", user) 
			                      .setParameter("nameMazo", nomDeck)
			                      .setParameter("nombreCarta", nomCarta)
			                      .getSingleResult();
		c.setnCopias(nCopias);
		
		sis.beginTransaction();
		sis.merge(c);
			
		sis.getTransaction().commit();
		sis.close();
	}
}
