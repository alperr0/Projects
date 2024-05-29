package com.dam.Deck_Constructor_TFG.Test;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.dam.Deck_Constructor_TFG.Modelo.Usuario;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

class TestPersist {

	@Test
	void test() {
		Session sis = HibernateUtil.getSessionFactory().openSession();
		Usuario user = new Usuario("Carolo", "1234");
		
		sis.beginTransaction();
		
		sis.persist(user);
		
		sis.getTransaction().commit();
		
		System.out.println(sis.get(Usuario.class, 1));
	}

}
